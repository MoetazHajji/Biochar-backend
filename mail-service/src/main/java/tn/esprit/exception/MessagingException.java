package tn.esprit.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class MessagingException extends RuntimeException{
    public MessagingException(String message){
        super (message);
    }
}
