package tn.esprit.Configures;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.User;
import tn.esprit.Models.Msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

     @Bean
    public ConsumerFactory<String, String> consumerFactory()
    {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id_String");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory StringKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return  factory;
    }



    @Bean
    public ProducerFactory<String, ?> producerFactory()
    {
        Map<String,Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return  new DefaultKafkaProducerFactory<>(config);
    }
    @Bean
    public KafkaTemplate<String, ?> kafkaTemplate()
    {
        return  new KafkaTemplate<>(producerFactory());
    }



}






//    @Bean
//    public ConsumerFactory<String, List<AccountDto>> ListAccountDtoConsumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id_ListAccountDto");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        ObjectMapper om = new ObjectMapper();
//        JavaType type = om.getTypeFactory().constructParametricType(List.class, AccountDto.class);
//        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<List<AccountDto>>(type, om, false));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, List<AccountDto>> ListAccountDtoKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, List<AccountDto>> factory = new ConcurrentKafkaListenerContainerFactory<String, List<AccountDto>>();
//        factory.setConsumerFactory(ListAccountDtoConsumerFactory());
//        return factory;
//    }




//    @Bean
//    public ConsumerFactory<String, AppointmentDto> AppointmentDtoConsumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id_AppointmentDto");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(AppointmentDto.class));
//
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, AppointmentDto> AppointmentDtoKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, AppointmentDto> factory = new ConcurrentKafkaListenerContainerFactory<String, AppointmentDto>();
//        factory.setConsumerFactory(AppointmentDtoConsumerFactory());
//        return factory;
//    }



//   @Bean
//    public ConsumerFactory<String, Msg> MsgConsumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id_Msg");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Msg.class));
//
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Msg> MsgKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Msg> factory = new ConcurrentKafkaListenerContainerFactory<String, Msg>();
//        factory.setConsumerFactory(MsgConsumerFactory());
//        return factory;
//    }