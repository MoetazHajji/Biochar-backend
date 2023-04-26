package tn.esprit.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Models.Msg;
import tn.esprit.Repositorys.AppointmentRepository;

import javax.mail.MessagingException;
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
    private IEmailSenderService iEmailSenderService;
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
          Msg msg = null;
           try {
               msg = new Msg("Reminder Tomorrow" ,"belhsenbachouch97@gmail.com",
                       iFileService.Edit_ReminderAppointementPage (appointment.getAccount().getUser().getUsername(), appointment,
                               "http://locahost:8099/biochar/")
                       );
               iEmailSenderService.SendEmailWithHtml(msg);
           } catch (IOException e) {
               log.warn(e.getMessage());
               throw new RuntimeException(e);
           } catch (MessagingException e) {
               log.warn(e.getMessage());
           }
        }
    }
}
