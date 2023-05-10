package tn.esprit.Service;

import org.springframework.stereotype.Service;
import tn.esprit.Config.SMTPConfig;
import tn.esprit.Models.Attachment;
import tn.esprit.Models.BodyContent;
import tn.esprit.Models.Msg;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service("server_SMTP")
public class SMTPMail implements  ISMTPMail {
    public static SMTPConfig emailConfig;
    public SMTPMail (SMTPConfig emailConfig ){
        this.emailConfig = emailConfig;
    }
    Session session;
    @Override
    public void connect () {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailConfig.getHost());
        properties.put("mail.smtp.auth", emailConfig.isAuth());
        properties.put("mail.smtp.starttls.enable", emailConfig.isEnableStarttls());
        this.session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });
    }
  @Override
    public void sending( Msg msg ) throws MessagingException  {
     this.connect();
      boolean dataEmpty = msg.getAttachements().stream().
              allMatch(n -> ( n.getData() == null || n.getData().length == 0));
      boolean nameFilePresent = msg.getAttachements().stream().
              allMatch(n -> ! n.getFileName().isEmpty());

      if ( msg.getAttachements().isEmpty() && msg.getBodyContents().size() > 0 ) {
          this.sendingMultiBodyContent(  msg );
          return ;
      }
      if ( msg.getAttachements().size() > 0  && !dataEmpty && nameFilePresent ) {
          this.sendingWithStreamDocuments(  msg );
          return ;
      }
      if ( msg.getAttachements().size() ==  1 && dataEmpty && nameFilePresent ) {
          this.sendingWithDocument(  msg );
          return ;
      }
      if ( msg.getAttachements().size() >  1 && dataEmpty && nameFilePresent ) {
          this.sendingWithDocuments(  msg );
      }
    }
    public void sendingMultiBodyContent( Msg msg ) throws MessagingException  {
            // create and send the email with attachment
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getEmail()));
            message.setSubject(msg.getSubject());

            // create the HTML body
            // create a list of attachments
            List<BodyPart> attachmentBodyParts = new ArrayList<>();
            for ( BodyContent content : msg.getBodyContents())
            {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(content.getContent(), content.getType());
                attachmentBodyParts.add(messageBodyPart);
            }
            // add the message body and attachments to the email
            Multipart multipart = new MimeMultipart();
            for (BodyPart attachmentBodyPart : attachmentBodyParts) {
                multipart.addBodyPart(attachmentBodyPart);
            }
            message.setContent(multipart);

            Transport.send(message);

    }
    @Override
    public void sendingSimple( Msg msg ) throws MessagingException  {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(msg.getEmail()));
            message.setSubject(msg.getSubject());
            message.setText(msg.getBody());
            Transport.send(message);
    }
    @Override
    public void sendingWithViewHTML( Msg msg ) throws MessagingException  {
            // create and send the email with attachment
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getEmail()));
            message.setSubject(msg.getSubject());

            // create the HTML body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(msg.getBody(), "text/html");

            // add the HTML body to the email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
    }
    @Override
    public void sendingWithDocument( Msg msg ) throws MessagingException {

            // create and send the email with attachment
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getEmail()));
            message.setSubject(msg.getSubject());

            // create the message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msg.getBody());

            // create the attachment
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(new File(msg.getAttachements().get(0).getFileName()));
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(msg.getAttachements().get(0)._getNameFile_());

            // add the message body and attachment to the email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            Transport.send(message);
    }
    @Override
    public void sendingWithDocuments( Msg msg ) throws MessagingException {
            // create and send the email with attachments
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getEmail()));
            message.setSubject(msg.getSubject());




            // create the message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msg.getBody());


            // create a list of attachments
            List<BodyPart> attachmentBodyParts = new ArrayList<>();
            for ( Attachment attachement : msg.getAttachements() )
            {
                attachmentBodyParts.add(createAttachmentBodyPart(attachement.getFileName()));
            }

            // add the message body and attachments to the email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
           for (BodyPart attachmentBodyPart : attachmentBodyParts) {
                multipart.addBodyPart(attachmentBodyPart);
            }
            message.setContent(multipart);
            Transport.send(message);
    }
    private  BodyPart createAttachmentBodyPart(String filePath) throws MessagingException {
        BodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(new File(filePath));
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(new File(filePath).getName());
        return attachmentBodyPart;
    }

    @Override
    public void sendingWithStreamDocuments( Msg msg ) throws MessagingException {
            // create and send the email with attachments
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getEmail()));
            message.setSubject(msg.getSubject());

            // create the message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msg.getBody());


            // create a list of attachments
            List<BodyPart> attachmentBodyParts = new ArrayList<>();
            for ( Attachment attachement : msg.getAttachements() )
            {
                DataSource source = new ByteArrayDataSource(attachement.getData(), "application/octet-stream");
                String fileType = "txt"; // your file type
                MimeBodyPart attachment = new MimeBodyPart();
                attachment.setDataHandler(new DataHandler(source));
                attachment.setFileName(attachement.getFileName());
                attachmentBodyParts.add(attachment);
            }

            // add the message body and attachments to the email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            for (BodyPart attachmentBodyPart : attachmentBodyParts) {
                multipart.addBodyPart(attachmentBodyPart);
            }
            message.setContent(multipart);
            Transport.send(message);
    }
}

