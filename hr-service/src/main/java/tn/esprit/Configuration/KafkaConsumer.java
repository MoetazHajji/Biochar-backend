package tn.esprit.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.Dto.AccountDtoTopic;
import tn.esprit.Entity.ExternelEntity.Account;
import tn.esprit.Mapper.AccountMapper;
import tn.esprit.Mapper.IObjectMapperConvert;
import tn.esprit.Repository.AccountRepository;

@Component
public class KafkaConsumer {

    @Autowired
    private KafkaTemplate<Object, AccountDtoTopic > kafkaTemplateAccountDto;
    @Autowired
    IObjectMapperConvert objectMapperConvert ;
    @Autowired
    AccountRepository accountRepository;

    @KafkaListener(topics = "topic-service-user-account-insert", groupId = "topic-service-user-account-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_insert  (String payload)
    {
        AccountDtoTopic accountDtoTopic  = null ;
        System.out.println("topic-service-account-insert :  = " + payload);
        try {  accountDtoTopic   = (AccountDtoTopic) objectMapperConvert.convertToObject(payload, AccountDtoTopic.class);
            Account account = AccountMapper.mapToEntity(accountDtoTopic);
            accountRepository.save(account);

        }
        catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-account-insert :  = " + e.getMessage());
        }
    }
    @KafkaListener(topics = "topic-service-account-update", groupId = "topic-service-user-account-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_update  (String payload)
    {
        AccountDtoTopic accountDto = null ;
        System.out.println("topic-service-account-update :  = " + payload);
        try {  accountDto   = (AccountDtoTopic) objectMapperConvert.convertToObject(payload, AccountDtoTopic.class);
            Account account = AccountMapper.mapToEntity(accountDto);
            accountRepository.save(account);
        }
        catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-account-update :  = " + e.getMessage());
        }
    }
    @KafkaListener(topics = "topic-service-account-delete", groupId = "topic-service-user-account-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume_delete (String payload)
    {

        AccountDtoTopic accountDto = null ;
        System.out.println("topic-service-account-delete :  = " + payload);
        try {  accountDto   = (AccountDtoTopic) objectMapperConvert.convertToObject(payload,AccountDtoTopic.class);
            Account account = AccountMapper.mapToEntity(accountDto);
            accountRepository.delete(account);
        }
        catch (JsonProcessingException e) {
            System.out.println("ERROR topic-service-account-delete :  = " + e.getMessage());
        }
    }
}
