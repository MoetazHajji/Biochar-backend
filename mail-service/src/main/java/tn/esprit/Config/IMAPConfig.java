package tn.esprit.Config;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor // sln for Parameter 0 of constructor in tn.spring.emailelient.EmailSenderService required a bean of type class that could not be found.
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IMAPConfig {
    //@Value("${spring.mail.host}")
    String host = "imap.gmail.com";//change accordingly
    String mailStoreType = "imaps";
    int port;
    String username= "belhsenbachouch55@gmail.com";
    String password= "oegamwscnzzvekpu";//change accordingly
    String filePath= "objects.json";
}
