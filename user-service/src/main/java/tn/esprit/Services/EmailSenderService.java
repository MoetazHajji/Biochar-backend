package tn.esprit.Services;


import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.Models.EmailConfig;
import tn.esprit.Models.Msg;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

@Service("EmailSender")
public class EmailSenderService implements  IEmailSenderService{

    private EmailConfig emailCfg;
    private JavaMailSender mailSender;

    public EmailSenderService(EmailConfig emailCfg) {
        this.emailCfg = emailCfg;
        this.mailSender = this.getJavaMailSender();
    }

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailCfg.getHost());
        mailSender.setPort(emailCfg.getPort());

        mailSender.setUsername(emailCfg.getUsername());
        mailSender.setPassword(emailCfg.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


    // @Autowired
    //  private JavaMailSender mailSender;
    @Override
    public String  SendSimpleEmail(Msg msg){

        SimpleMailMessage Msg = new SimpleMailMessage();
        Msg.setFrom(emailCfg.getUsername());
        Msg.setTo(msg.getEmail());
        Msg.setSubject(msg.getSubject());
        Msg.setText(msg.getBody());
        mailSender.send(Msg);
        return "Mail Simple Send..";
    }
    @Override
    public String  SendEmailWithAttachement(Msg msg) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom(emailCfg.getUsername());
        mimeMessageHelper.setTo(msg.getEmail());
        mimeMessageHelper.setSubject(msg.getSubject());
        mimeMessageHelper.setText(msg.getBody());
        FileSystemResource fileSystemResource = new FileSystemResource( new File(msg.getAttachement()));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        mailSender.send(mimeMessage);
        return "Mail with Attachement Send..";
    }
}
