package tn.esprit.Interface;

import javax.mail.MessagingException;

public interface ISchuduld {


    //@Scheduled(cron = "* 1 * * * * ?")
  //  @Scheduled(cron = "0 0 1 ? * *")
    void Reminder() throws MessagingException;
}
