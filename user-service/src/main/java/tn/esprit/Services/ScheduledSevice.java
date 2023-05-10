package tn.esprit.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.Configures.MyConfigInitParameters;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.BodyContent;
import tn.esprit.Entitys.Msg;
import tn.esprit.Repositorys.AppointmentRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@Component
@EnableAsync
@Slf4j
public class ScheduledSevice {
    @Autowired
    private  AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private KafkaTemplate<Object,  Msg > kafkaTemplate;
    @Autowired
    private IFileService iFileService;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Async
    @Scheduled (cron = "0 30 8 * * ?")//  (fixedDelay = 2*1000)//(cron = "* 0 14 * * ?")
    public void  Reminder(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByAppointmentDateAndAppointment_StatusAvaible(tomorrow);
       for (Appointment appointment: appointments){
           log.info("sending mail for " + appointment.getAccount().getUser().getUsername());
           try {
               Msg msg = new Msg();
               msg.setSubject("Reminder Tomorrow");
               msg.setEmail(appointment.getAccount().getEmail());
               msg.getBodyContents().add(new BodyContent("text/plain","body"));
               String file =  iFileService.Edit_ReminderAppointementPage (appointment.getAccount().getUser().getUsername(), appointment);
               msg.getBodyContents().add(new BodyContent("text/html",  file));
               kafkaTemplate.send("topic-service-mail-sender-send-mail",msg);
               kafkaTemplate.flush();
           } catch (IOException e) {
               log.warn(e.getMessage());
               throw new RuntimeException(e);
           } catch (Exception e) {
               log.warn(e.getMessage());
           }
        }
    }
}
