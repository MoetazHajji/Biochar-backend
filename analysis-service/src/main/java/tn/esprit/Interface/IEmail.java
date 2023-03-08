package tn.esprit.Interface;

import javax.mail.MessagingException;

public interface IEmail {


    //void sendMessageWithAttachmentpatient(String to, String subject, String text, String pathToAttachment) throws MessagingException;

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachmentpatient(String pathToAttachment) throws MessagingException;
}
