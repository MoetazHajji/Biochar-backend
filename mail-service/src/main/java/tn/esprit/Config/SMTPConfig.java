package tn.esprit.Config;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor// sln for Parameter 0 of constructor in tn.spring.emailelient.EmailSenderService required a bean of type class that could not be found.
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SMTPConfig {
    String host = "smtp.gmail.com"; // SMTP server hostname
    boolean auth = true  ;
    boolean enableStarttls = true ;
    String username= "belhsenbachouch55@gmail.com";
    String password= "oegamwscnzzvekpu";//change accordingly


}
