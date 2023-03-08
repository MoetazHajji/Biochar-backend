package tn.esprit.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import tn.esprit.Interface.IEmail;
import tn.esprit.Repository.AccountRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
@Component
public class EmailService implements IEmail {

@Autowired
private JavaMailSender emailSender;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("siwar.atiya@esprit.tn");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    @Override
    public void sendMessageWithAttachmentpatient(String pathToAttachment) throws MessagingException {
       // Account account= accountRepository.findById(id).orElse(null);
       // account.setFirstname("siwar");
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String to="siwar.atiya@esprit.tn";
        String subject = "Analysiz Result";
        String text = "<html><body style='font-family: Arial, sans-serif;'>"
                + "<p style='color: #1c87c9; font-size: 18px;'>Dear " +  ",</p>"
                + "<p style='color: #333333; font-size: 16px;'>Your Result Test</p>"
                + "<table style='border-collapse: collapse;'><tr>"
                + "<td style='border: 1px solid #1E90FF; padding: 10px; color: #008000; font-size: 16px; font-weight: bold;'>Verification Result</td>"
                + "<td style='border: 1px solid #1E90FF; padding: 10px; color: #DC143C; font-size: 16px; font-weight: bold;'></td>"
                + "</tr></table>"
                + "<p style='color: #333333; font-size: 16px;'>Thank you .</p>"
                + "</body></html>";


        helper.setFrom("siwar.atiya@esprit.tn");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text,true);
        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("patients.pdf", file);

        emailSender.send(message);


    }
}
