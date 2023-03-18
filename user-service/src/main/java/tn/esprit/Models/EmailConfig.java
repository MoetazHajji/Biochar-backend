package tn.esprit.Models;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor // sln for Parameter 0 of constructor in tn.spring.emailelient.EmailSenderService required a bean of type class that could not be found.
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailConfig {
    @Value("${spring.mail.host}")
     String host;

    @Value("${spring.mail.port}")
     int port;

    @Value("${spring.mail.username}")
     String username;

    @Value("${spring.mail.password}")
     String password;
}
