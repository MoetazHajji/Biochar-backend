package tn.esprit.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.Config.SMTPConfig;
import tn.esprit.Mappers.IObjectMapperConvert;
import tn.esprit.Models.Msg;
import tn.esprit.Service.ISMTPMail;
import tn.esprit.Service.SMTPMail;
import tn.esprit.exception.ErrorDetails;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Component
@Slf4j
public class SenderConsumer {
    @Autowired
    private IObjectMapperConvert objectMapperConvert ;
    @Autowired
    private ISMTPMail mailSender;

    @Autowired
    private KafkaTemplate<Object, ErrorDetails> produceErrorDetails;
    private Msg msg;
    public SenderConsumer ( ) {

    }


    public String getSenderId() {
        Map<String, Object> configs = produceErrorDetails.getProducerFactory().getConfigurationProperties();
        String clientId = (String) configs.get(ProducerConfig.CLIENT_ID_CONFIG);
        return clientId;
    }
    @KafkaListener(topics = "topic-service-mail-sender-error-global", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_error_global  (String payload)
    {
        System.out.println("topic-service-mail-sender-error-global   :  = " + payload  );
        System.out.println( getSenderId() );
    }


    @KafkaListener(topics = "topic-service-mail-sender-configuration", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_configuration  (String payload)
    {
        try { SMTPMail.emailConfig   = (SMTPConfig) objectMapperConvert.convertToObject(payload,SMTPConfig.class);
        }
        catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-configuration :  = " + e.getMessage());
            produceErrorDetails.send("topic-service-mail-sender-error-global",
                    new ErrorDetails(LocalDate.now(),
                            LocalTime.now(),
                            e.getMessage(),
                            "topic-service-mail-sender-configuration") );
            produceErrorDetails.flush();
        }
        System.out.println("KafkaConsumer consume : -mail-sender-configuration = " +  SMTPMail.emailConfig );
    }


    @KafkaListener(topics = "topic-service-mail-sender-send-mail", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_send_mail  (String payload)
    {
        System.out.println("KafkaConsumer topic-service-mail-sender-simple-mail : send-mail = " + payload);
        try {
            this.msg = (Msg) objectMapperConvert.convertToObject(payload,Msg.class);
            mailSender.sending(this.msg);
            System.out.println("Email sent successfully.");
        } catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
            produceErrorDetails.send("topic-service-mail-sender-error-global",
                    new ErrorDetails(LocalDate.now(),
                            LocalTime.now(),
                            e.getMessage(),
                            "topic-service-mail-sender-send-mail") );
            produceErrorDetails.flush();
        }
        catch ( MessagingException  e) {
        System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
            produceErrorDetails.send("topic-service-mail-sender-error-global",
                    new ErrorDetails(LocalDate.now(),
                            LocalTime.now(),
                            e.getMessage(),
                            "topic-service-mail-sender-send-mail") );
            produceErrorDetails.flush();
        }
    }


    @KafkaListener(topics = "topic-service-mail-sender-simple-mail", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_simple_mail  (String payload)
    {
        System.out.println("KafkaConsumer topic-service-mail-sender-simple-mail : simple-mail = " + payload);
        try {
           this.msg = (Msg) objectMapperConvert.convertToObject(payload,Msg.class);
           mailSender.connect();
           mailSender.sendingSimple(this.msg);
            System.out.println("Email sent successfully.");
        } catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-simple-mail :  = " + e.getMessage());
        }
        catch ( MessagingException  e) {
            System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
        }
    }
    @KafkaListener(topics = "topic-service-mail-sender-mail-one-file", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_mail_file   (String payload)
    {
        System.out.println("KafkaConsumer topic-service-mail-sender-mail-one-file  : mail-one-file = " + payload);
        try {
            this.msg = (Msg) objectMapperConvert.convertToObject(payload,Msg.class);
            mailSender.connect();
            mailSender.sendingWithDocument(this.msg);
            System.out.println("Email sent successfully.");
        } catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-mail-one-file :  = " + e.getMessage());
        }
        catch ( MessagingException  e) {
            System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
        }
    }
    @KafkaListener(topics = "topic-service-mail-sender-mail-multiple-file", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_mail_multiple_file (String payload)
    {
        System.out.println("KafkaConsumer consume : mail-multiple-file = " + payload);
        try {
            this.msg = (Msg) objectMapperConvert.convertToObject(payload,Msg.class);
            mailSender.connect();
            mailSender.sendingWithDocuments(this.msg);
            System.out.println("Email sent successfully.");
        } catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-mail-multiple-file :  = " + e.getMessage());
        }
        catch ( MessagingException  e) {
            System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
        }
    }
    @KafkaListener(topics = "topic-service-mail-sender-mail-html-file", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_mail_html_file (String payload)
    {
        System.out.println("KafkaConsumer consume : mail-html-file = " + payload);
        try {
            this.msg = (Msg) objectMapperConvert.convertToObject(payload,Msg.class);
            mailSender.connect();
            mailSender.sendingWithViewHTML(this.msg);
            System.out.println("Email sent successfully.");
        } catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-mail-html-file :  = " + e.getMessage());
        }
        catch ( MessagingException  e) {
            System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
        }
    }
    @KafkaListener(topics = "topic-service-mail-sender-mail-file-stream", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_mail_file_stream  (String payload)
    {
        System.out.println("KafkaConsumer consume : simple-mail = " + payload);
        try {
            this.msg = (Msg) objectMapperConvert.convertToObject(payload,Msg.class);
            mailSender.connect();
            mailSender.sendingWithStreamDocuments( this.msg );
            System.out.println("Email sent successfully.");
          } catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-mail-sender-mail-file-stream :  = " + e.getMessage());
        }
        catch ( MessagingException  e) {
            System.out.println("ERROR topic-service-mail-sender-send-mail :  = " + e.getMessage());
        }

    }
}

