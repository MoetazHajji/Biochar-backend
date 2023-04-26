package tn.esprit.Configures;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.exception.Mappers.IObjectMapperConvert;

@Component
@Slf4j
public class KafkaConsumer {


    @Autowired
    IObjectMapperConvert objectMapperConvert ;



     @KafkaListener(topics = "TopicString", groupId = "group_id_String", containerFactory = "StringKafkaListenerContainerFactory")
    public void consume  (String msg)
    {
        log.info("KafkaConsumer consume : message = " + msg);

//        try {
//        AccountDto accountDto = (AccountDto) objectMapperConvert.convertToObject(msg , AccountDto.class);
//            System.out.println("Mssage " +accountDto.getEmail());
//        } catch (JsonProcessingException e) {
//           System.out.println("Error" +e.getMessage());
//        }

    }

}







//    @KafkaListener(topics = "TopicMsg", groupId = "group_id_Msg", containerFactory = "MsgKafkaListenerContainerFactory")
//    public void consumeMsg  (Msg message)
//    {
//
//        System.out.println("message = " + message.getEmail());
//
//    }



//    @KafkaListener(topics = "TopicListAccountDto", groupId = "group_id_ListAccountDto",
//            containerFactory = "ListAccountDtoKafkaListenerContainerFactory")
//    public void consumeListAccountDto  (List<AccountDto> message)
//    {
//
//        System.out.println("message = " + message.size());
//
//    }
  /*ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("message = " + message);
            List<AccountDto> participantJsonList = mapper.readValue(message, new TypeReference<List<AccountDto>>(){});
            System.out.println("AccountDto = " + participantJsonList.size()) ;
        } catch (JsonProcessingException e) {
            System.out.println("error = " + e.getMessage()) ;
        }*/

// System.out.println("gggggggggg = " + list.getUsername());

   /* @KafkaListener(topics = "TopicAppointmentDto", groupId = "group_id_AppointmentDto", containerFactory = "AppointmentDtoKafkaListenerContainerFactory")
    public void consume2  (AppointmentDto appointmentDto)
    {
        System.out.println("message = " + appointmentDto.getReason());
    }*/