package tn.esprit.Service;




import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.IOException;


public interface IIMAPMail {
    void connect () throws MessagingException;
    void receiveAllEmail() throws  MessagingException, IOException ;
}
