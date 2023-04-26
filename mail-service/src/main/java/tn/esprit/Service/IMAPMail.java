package tn.esprit.Service;

import com.sun.mail.imap.IMAPStore;
import com.sun.mail.util.BASE64DecoderStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tn.esprit.Config.IMAPConfig;
import tn.esprit.Mappers.IObjectMapperConvert;
import tn.esprit.Models.Attachment;
import tn.esprit.Models.BodyContent;
import tn.esprit.Models.Msg;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service("server_IMAP")
public class IMAPMail implements IIMAPMail{
    public IMAPConfig emailConfig;
    public IMAPMail (IMAPConfig emailConfig){
        this.emailConfig = emailConfig;
    }
    IMAPStore emailStore;

    List <Msg> msgs = new ArrayList<Msg>();



    @Autowired
    private KafkaTemplate<Object,  List<Msg> > kafkaTemplate;
    @Autowired
    IObjectMapperConvert objectMapperConvert ;

    @Override
    public void connect () throws MessagingException {
        //1) get the session object
        Properties properties = new Properties();
        properties.put("mail.imap.host", emailConfig.getHost());
        Session emailSession = Session.getDefaultInstance(properties);

        //2) create the POP3 store object and connect with the pop server
        this.emailStore = (IMAPStore) emailSession.getStore(emailConfig.getMailStoreType());
        this.emailStore.connect(emailConfig.getHost() , emailConfig.getUsername(), emailConfig.getPassword());
    }
    @Override
    public  void receiveAllEmail() throws  MessagingException  , IOException   {
            //3) create the folder object and open it
            Folder emailFolder = this.emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);


            long startTime = System.currentTimeMillis();

             //importFileToRecieveMsgs( emailConfig.getFilePath() );
             msgs = (List<Msg>) objectMapperConvert.importFileToCollection( emailConfig.getFilePath(),List.class, Msg.class);

            //4) retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                if( i > msgs.size() - 1) {
                    msgs.add( convertToMsg(  message  ));
                    System.out.println("count i :"+ i);
                }
            }


            // Your code here
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time in milliseconds: " + timeElapsed);

            System.out.println("messages: " + messages.length + " msgs :" +msgs.size());

            System.out.println("----------------finich-----------------");
            //5) close the store and folder objects
            emailFolder.close(false);
            emailStore.close();

            kafkaTemplate.send("topic-service-mail-imap-recierver-all-mails",msgs);
            kafkaTemplate.flush();


    }
    @Async
    public Msg convertToMsg( Message message) throws MessagingException, IOException {
        Msg msg = new Msg();
        msg.setEmail(    ( message.getFrom() == null ? null : ((InternetAddress) message.getFrom()[0]).getAddress())    );  //https://stackoverflow.com/questions/5214372/getting-only-email-address-to-display-when-using-message-getfrom-in-javamail
        msg.setSubject(message.getSubject());
        msg.setSentDate(message.getSentDate());
        msg.setReceivedDate(message.getReceivedDate());
        if ( message.getContent().getClass() == String.class )
        {
//            System.out.println("---------------String------------------");
            msg.setBody( message.getContent().toString() );
        }
        if ( message.getContent().getClass() == MimeMultipart.class ) {
//            System.out.println("----------------MimeMultipart-----------------");
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            for (int i = 0; i <  mimeMultipart.getCount(); i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if (bodyPart instanceof MimeBodyPart) {
                    MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;
//                    System.out.println("----------------   MimeBodyPart  -----------------");
//                    System.out.println(" Content Type: " + bodyPart.getContentType());
//                    System.out.println(" Content : " +  bodyPart.getContent().getClass());
                }
                if (!isMessageBody( bodyPart)) {
//                    System.out.println("!!!!!!!!    debut file   !!!!!!!!!!!!!!!!!!");
                    msg.getAttachements().add( convertbodyPartToAttachement (  bodyPart ) ) ;
//                    System.out.println("!!!!!!!!    end  file   !!!!!!!!!!!!!!!!!!");
                }
                if (isMessageBody( bodyPart)) {
//                    System.out.println("!!!!!!!!    debut body   !!!!!!!!!!!!!!!!!!");
                    if (bodyPart.getContentType().toLowerCase().startsWith("multipart/alternative")) {
//                        System.out.println("!!!!!!!!    multipart/alternative   !!!!!!!!!!!!!!!!!!");
                        MimeMultipart multipart = (MimeMultipart) bodyPart.getContent();
//                        System.out.println("!!!!!!!!       "+ multipart.getCount()+"    !!!!!!!!!!!!!!!!!!");
                        for (int j = 0; j < multipart.getCount(); j++) {
                            BodyPart part = multipart.getBodyPart(j);
//                            System.out.println(part.getContentType());
//                            System.out.println(part.getContent().toString());
                            if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
                                // This is a message body
                                msg.getBodyContents().add(new BodyContent(part.getContentType() , (String) part.getContent()));
//                                System.out.println((String) part.getContent());
                            }
                        }
                    }
                    if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("text/html") ) {
//                        System.out.println("!!!!!!!!  text/plain  text/html   !!!!!!!!!!!!!!!!!!");
                        msg.getBodyContents().add(new BodyContent(bodyPart.getContentType() , (String) bodyPart.getContent()));
                    }
//                    System.out.println("!!!!!!!!    end  body   !!!!!!!!!!!!!!!!!!");
                }
            }
        }


      return msg;
    }

    public boolean isMessageBody(BodyPart bodyPart) throws MessagingException {
        String disposition = bodyPart.getDisposition();
        if (disposition == null || !disposition.toLowerCase().startsWith("attachment")) {
            // This is likely a message body
            return true;
        } else {
            // This is a file attachment
            return false;
        }
    }

    public Attachment convertbodyPartToAttachement ( BodyPart bodyPart ) throws MessagingException, IOException {
        // Assuming the file name is obtained from the MIME headers
//        System.out.println(  bodyPart.getFileName());
//        System.out.println( bodyPart.getContentType());
//        System.out.println( (  bodyPart.getContent() instanceof InputStream ? "InputStream" : "" ) );
//        System.out.println( (  bodyPart.getContent() instanceof BASE64DecoderStream ? "BASE64DecoderStream" : "" ) );
        String fileName =   ( bodyPart.getFileName() == null ? ""  : bodyPart.getFileName()  ) ;
        String typeFile =  ( bodyPart.getContentType() == null ? ""  : bodyPart.getContentType()  ) ;
        System.out.println( bodyPart.getContent().getClass() ) ;
        byte[] fileData = (((bodyPart.getContent() instanceof InputStream
                                             ||
                             bodyPart.getContent() instanceof BASE64DecoderStream))
                                              ?
                             IOutils.readAllBytes((InputStream) bodyPart.getContent())
                                              :
                                         new byte[0]);
//        System.out.println( "byteArray.length : "+fileData.length);

        return  new Attachment( fileName,typeFile, fileData);
    }













}







//https://stackoverflow.com/questions/61176/getting-mail-from-gmail-into-java-application-using-imap/61469#61469
//props.setProperty("mail.imaps.host", "imap.gmail.com");
//        props.setProperty("mail.imaps.port", "993");
//        props.setProperty("mail.imaps.connectiontimeout", "5000");
//        props.setProperty("mail.imaps.timeout", "5000");



                    /*if (mimeBodyPart.isMimeType("text/*")) { //text/* //text/plain  //text/html
                        // Text body part
                      //  String text = (String) bodyPart.getContent();
                     //   System.out.println("Text body part: " + text);
                        System.out.println("----------------   texttttt  -----------------");
                    }*/

//                System.out.println("---------------------------------");
//                System.out.println("Email Number " + (i + 1));
//                System.out.println("From: " + message.getFrom()[0]);
//                 System.out.println("Subject: " + message.getSubject());
//                System.out.println("Sent Date: " + message.getSentDate());
//                System.out.println("Received Date: " + message.getReceivedDate());
//                System.out.println("Message: " + message.getContent().toString());
//                System.out.println("Type Message : " +  message.getContent().getClass());