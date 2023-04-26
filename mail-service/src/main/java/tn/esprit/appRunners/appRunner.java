package tn.esprit.appRunners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.Config.SMTPConfig;
import tn.esprit.Mappers.IObjectMapperConvert;
import tn.esprit.Models.Attachment;
import tn.esprit.Models.BodyContent;
import tn.esprit.Models.Msg;
import tn.esprit.Service.IIMAPMail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component

public class appRunner implements CommandLineRunner {

    @Autowired
    private IIMAPMail iimapMail;
    @Autowired
    IObjectMapperConvert objectMapperConvert ;
    @Autowired
    private KafkaTemplate<Object,  Msg > kafkaTemplate;
    @Autowired
    private KafkaTemplate<Object,  SMTPConfig > kafkaTemplateSMTPConfig;
    @Override
    public void run(String... args) throws Exception {

//        kafkaTemplateSMTPConfig.send("topic-service-mail-sender-configuration",
//                new SMTPConfig(
//                 "smtp.gmail.com",true,true ,"belhsenbachouch55@gmail.com","oegamwscnzzvekpu")
//                );
//        kafkaTemplateSMTPConfig.flush();


       // SMTPConfig smtpConfig = new SMTPConfig ("smtp.gmail.com",true  ,true ,"belhsenbachouch55@gmail.com", "oegamwscnzzvekpu");
       // kafkaTemplate.send("topic-service-mail-sender-configuration",objectMapperConvert.convertToJsonString(smtpConfig));
      //  kafkaTemplate.flush();


//        Msg msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1");
//        msg.getBodyContents().add(new BodyContent("text/plain","body !"));
//        msg.getBodyContents().add(new BodyContent("text/html", "<html><body><h1>This is a test email sent from Java using the JavaMail API.</h1><p>This email was sent in HTML format.</p></body></html>"));
//        kafkaTemplate.send("topic-service-mail-sender-send-mail",msg);
//        kafkaTemplate.flush();
//        Msg msg2 = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1");
//        msg2.getAttachements().add(new Attachment("file.json"));
//        kafkaTemplate.send("topic-service-mail-sender-send-mail",msg2);
//        kafkaTemplate.flush();
//        msg2.getAttachements().add(new Attachment("file2.json"));
//        kafkaTemplate.send("topic-service-mail-sender-send-mail",msg2);
//        kafkaTemplate.flush();
//        Msg msg3 = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1");
//        byte[] byteArray2 = {0x12, 0x34, (byte)0xFF, (byte)0xAA};
//        msg3.getAttachements().add(new Attachment("file2.json","" ,byteArray2 ));
//        kafkaTemplate.send("topic-service-mail-sender-send-mail",msg3);
//        kafkaTemplate.flush();

        /* msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1","path/to/attachment.txt");
        kafkaTemplate.send("topic-service-mail-sender-mail-one-file",msg);
        kafkaTemplate.flush();
         msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1","path/to/attachment.txt");
        kafkaTemplate.send("topic-service-mail-sender-mail-multiple-file",msg);
        kafkaTemplate.flush();
         msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1","path/to/attachment.txt");
        kafkaTemplate.send("topic-service-mail-sender-mail-html-file",msg);
        kafkaTemplate.flush();*/



        //mailReceiver.connect();
        //mailReceiver.receiveEmail();
       // mailSender.connect();
        //mailSender.sending(new Msg("subject","belhsenbachouch97@gmail.com","exemple 1"));
        //mailSender.sendingWithDocument(new Msg("subject","belhsenbachouch97@gmail.com","exemple 1"
         //       ,"./src/main/resources/folder/ex.json"));
       // mailSender.sendingWithDocuments(new Msg("subject","belhsenbachouch97@gmail.com","exemple 3"
        //       ,new String[]{"./src/main/resources/folder/ex.json", "./src/main/resources/folder/ex2.json"}));
       // String htmlBody = "<html><body><h1>This is a test email sent from Java using the JavaMail API.</h1><p>This email was sent in HTML format.</p></body></html>";
        // mailSender.sendingWithViewHTML(new Msg("subject","belhsenbachouch97@gmail.com",htmlBody));


        // Msg msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 1","path/to/attachment.txt");
       // System.out.println(msg.getNameFile());



       //  Attachment attachment  =  getAttachement("./src/main/resources/folder/ex.json") ;
        // kafkaTemplate.send("topic-service-mail-sender-mail-file-stream", attachment);
        // kafkaTemplate.flush();






//        Msg msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 555");
//        kafkaTemplate.send("topic-service-mail-sender-simple-mail",msg);
//        kafkaTemplate.flush();
//        Attachment attachment  =  new Attachment("./src/main/resources/folder/ex.json") ;
//        Msg msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 555",new ArrayList<Attachment>(){{add(attachment);}});
//        kafkaTemplate.send("topic-service-mail-sender-mail-one-file",msg);
//        kafkaTemplate.flush();
//        Msg msg = new Msg("subject","belhsenbachouch97@gmail.com","exemple 555",
//                new ArrayList<Attachment>(){{
//                    add( new Attachment("./src/main/resources/folder/ex.json"));
//                    add( new Attachment("./src/main/resources/folder/texte1.txt"));
//                }});
//        kafkaTemplate.send("topic-service-mail-sender-mail-multiple-file",msg);
//        kafkaTemplate.flush();
//        String htmlBody = "<html><body><h1>This is a test email sent from Java using the JavaMail API.</h1><p>This email was sent in HTML format.</p></body></html>";
//        Msg msg = new Msg("subject","belhsenbachouch97@gmail.com",htmlBody);
//        kafkaTemplate.send("topic-service-mail-sender-mail-html-file",msg);
//        kafkaTemplate.flush();


//        Attachment attachment  =  getAttachement("./src/main/resources/folder/ex.json") ;
//        Attachment attachment2  =  getAttachement("./src/main/resources/folder/ex2.json") ;
//        Msg msg = new Msg("subject","belhsenbachouch97@gmail.com","no body",
//                 new ArrayList<Attachment>(){{
//                    add( attachment );
//                     add( attachment2 );
//                }});
//         kafkaTemplate.send("topic-service-mail-sender-mail-file-stream", msg);
//         kafkaTemplate.flush();


     //   iimapMail.connect();
   //    iimapMail.receiveAllEmail();
//        try {
////            List<Msg> msgs = (List<Msg>) objectMapperConvert.importFileToCollection("ex.json",List.class, Msg.class);
////            Msg msg =  msgs.get(0);
////            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
////            System.out.println(msg.getSubject());
//
//        //   IMAPConfig iMAPConfig = (IMAPConfig) objectMapperConvert.importFileToObject("ex.json" , IMAPConfig.class);
//         //   Msg msg = (Msg) objectMapperConvert.importFileToObject("ex.json" ,Msg.class);
//         //   System.out.println(msg.getSubject());
//        }
//        catch (Exception e ){   System.out.println(e.getMessage()); }
////        Thread.sleep(10000L);
////        iimapMail.connect();
////        iimapMail.receiveAllEmail();





//        try {   inputStream = new FileInputStream("./src/main/resources/folder/ex2.json");  } catch (FileNotFoundException e) {  e.printStackTrace(); }
//        try {  outputStream = new FileOutputStream("./src/main/resources/folder/ex3.json"); } catch (FileNotFoundException e) {    e.printStackTrace(); }
//        // Read from the input stream and write to the output stream
//        int data;
//        try {    while ((data = inputStream.read()) != -1) {  outputStream.write(data);  }  } catch (IOException e) {   e.printStackTrace(); }
//        // Close the streams
//        try {  inputStream.close();  outputStream.close();} catch (IOException e) {  e.printStackTrace(); }
    }
    FileInputStream inputStream = null;
    FileOutputStream outputStream = null;


    private  String getFileType(String fileName) {
        String fileType = "unknown";
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0) {
            fileType = fileName.substring(lastIndexOfDot + 1);
        }
        return fileType;
    }

    private  Attachment getAttachement(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        System.err.println(" file: " +fileName);
        String fileType = getFileType(fileName);
        byte[] data = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        } catch (IOException ex) {
            System.err.println("Failed to read file: " + ex.getMessage());
            return null;
        }
        return new  Attachment( fileName,  fileType,  data);
    }

}
