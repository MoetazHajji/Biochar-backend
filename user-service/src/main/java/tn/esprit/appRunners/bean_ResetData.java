package tn.esprit.appRunners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tn.esprit.Entitys.*;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AppointmentRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Services.IGenericCRUD;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component
public class bean_ResetData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean One of Reset Data  run method Started !!" );
        //this.reset( );
    }

    @Autowired
    private IGenericCRUD<User> IUserService;
    @Autowired
    private IGenericCRUD<Account> IAccountService;
    @Autowired
    private IGenericCRUD<Appointment> IAppointmentService;


    public User user1 = new User(  "Belhsen97" , "97747369"     );
    public User user2 = new User(  "Belhsen97" , "97747369"     );
    public User user3 = new User(  "Belhsen97" , "97747369"     );
    public User user4 = new User(  "Belhsen97" , "97747369"     );

    public Appointment appointment1 = new Appointment(  "non reason" , this.convertCalendarToDate (2023 , 6 , 18) ,
           "no comments" , false );
    public Appointment appointment2 = new Appointment(  "non reason" , this.convertCalendarToDate (2023 , 6 , 18) ,
            "no comments" , false );
    public Appointment appointment3 = new Appointment(  "non reason" , this.convertCalendarToDate (2023 , 6 , 18) ,
            "no comments" , false );
    public Appointment appointment4 = new Appointment(  "non reason" , this.convertCalendarToDate (2023 , 6 , 18) ,
            "no comments" , false );

    public Account account1 = new Account(  "Belhsen" , "bachouch", 10820305,  55775085 ,
            this.convertCalendarToDate (1996 , 6 , 18),
            this.convertCalendarToDate (2023 , 2 , 16)
    ,"belhsenbachouch@yahoo.fr",Gender.male);

    private Date convertCalendarToDate (int Year , int Mounth , int day) {
        Calendar cal = Calendar.getInstance();
        Mounth--;
        cal.set(Year, Mounth, day);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String date1 = format.format(date);
        Date getDate = null;
        try {
            getDate = format.parse(date1);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return  getDate;
    }

    private void reset( ){
         IUserService.Insert(user1);
         IUserService.Insert(user2);
         IUserService.Insert(user3);
         IUserService.Insert(user4);
         IAccountService.Insert(account1);
         IAppointmentService.Insert(appointment1);
         IAppointmentService.Insert(appointment2);
         IAppointmentService.Insert(appointment3);
         IAppointmentService.Insert(appointment4);
    }
}