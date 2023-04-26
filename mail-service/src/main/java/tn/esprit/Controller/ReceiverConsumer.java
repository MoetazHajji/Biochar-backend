package tn.esprit.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.Mappers.IObjectMapperConvert;

@Component
@Slf4j
public class ReceiverConsumer {
    @Autowired
    IObjectMapperConvert objectMapperConvert ;
    @KafkaListener(topics = "topic-service-mail-imap-configuration", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_configuration  (String msg)
    {
        System.out.println("KafkaConsumer consume : simple-mail = " + msg);
    }
    @KafkaListener(topics = "topic-service-mail-imap-recierver", groupId = "topic-service-mail-sender-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_simple_mail  (String msg)
    {
        System.out.println("KafkaConsumer consume : simple-mail = " + msg);
    }
}