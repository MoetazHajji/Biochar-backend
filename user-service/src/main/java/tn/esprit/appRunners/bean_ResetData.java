package tn.esprit.appRunners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import tn.esprit.Entitys.*;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AppointmentRepository;
import tn.esprit.Repositorys.TimeOffRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Services.IGenericCRUD;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component
public class bean_ResetData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean One of Reset Data  run method Started !!" );
        this.reset( );

      /* if(isWeekend(convertCalendarToDate (2023 , 2 , 20))) {
            System.out.println("The date is a Weekend");
        }
        else {
            System.out.println("The date is not a Weekend");
        }*/
        //System.out.println( timeOffRepository. isInThatDate( this.convertCalendarToDate (2023 , 2 , 23) ) );
        //System.out.println( timeOffRepository.isInBetweenTwoTime(this.convertCalendarToTime (14 , 0,0 ),this.convertCalendarToTime (17 , 0,0 )));
      /*  System.out.println( appointmentRepository. isInBetweenTwoTimeAndDate(
                convertCalendarToTime (15 , 00,00  ) ,
                convertCalendarToTime (15 , 30,00  ),
                this.convertCalendarToDate (2023 , 2 , 23) ));*/
        //System.out.println(  userRepository.isCorrectEmail("belhsenbachouch97@gmail.com") );

    }

  /*  @Autowired
    private IGenericCRUD<User> IUserService;
    @Autowired
    private IGenericCRUD<Account> IAccountService;
    @Autowired
    private IGenericCRUD<Appointment> IAppointmentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IGenericCRUD<TimeOff> ITimeOffService;*/



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
    private Date convertCalendarToTime (int hour , int min,int second  ) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR,hour);
        cal.set(Calendar.MINUTE,min);
        cal.set(Calendar.SECOND,second);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

        String date1 = format.format(date);
        Date getDate = null;
        try {
            getDate = format.parse(date1);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return  getDate;
    }

    private void reset( ){
         /*IUserService.deleteAll();
         IUserService.Insert(user1);
         IUserService.Insert(user2);
         IUserService.Insert(user3);
         IUserService.Insert(user4);
         IUserService.Insert(user5);
         IUserService.Insert(user6);*/
      //   IAccountService.Insert(account1);
      //  IAppointmentService.Insert(appointment1);
      //   IAppointmentService.Insert(appointment2);
        // IAppointmentService.Insert(appointment3);
       //  IAppointmentService.Insert(appointment4);
       //  ITimeOffService.Insert(TimeOff_Pause) ;
        // ITimeOffService.Insert( TimeOff_horstravail) ;
        // ITimeOffService.Insert(TimeOff_evenement) ;
       // ITimeOffService.Insert(TimeOff_convre_feu) ;
    }
    public User user1 = new User(  "Chief_Service" , "0000" , Roles.Chief_Service,"ACCESS_TEST_Receptionist"     );
    public User user2 = new User(  "Doctor" , "1111" , Roles.Doctor,"NO_ACCESS"    );
    public User user3 = new User(  "Biologist" , "2222" , Roles.Biologist,"NO_ACCESS"    );
    public User user4 = new User(  "Receptionist" , "3333" ,Roles.Receptionist,"ACCESS_TEST_Receptionist"    );
    public User user5 = new User(  "Intern" , "4444" , Roles.Intern,"NO_ACCESS"    );
    public User user6 = new User(  "Patient" , "5555" , Roles.Patient,"ACCESS_TEST_Patient"     );



    public Appointment appointment1 = new Appointment("non reason 1"  ,
            "no comments 1" ,
            false ,
            this.convertCalendarToDate (2023 , 2 , 23) ,
            convertCalendarToTime (15 , 00,00  ) ,
            convertCalendarToTime (15 , 30,00  )) ;
    public Appointment appointment2 = new Appointment("non reason 2"  ,
            "no comments 2" ,
            false ,
            this.convertCalendarToDate (2023 , 2 , 23) ,
            convertCalendarToTime (3 , 00,00  ) ,
            convertCalendarToTime (3 , 30,00  )) ;
    public Appointment appointment3 = new Appointment("non reason 3"  ,
            "no comments 3" ,
            false ,
            this.convertCalendarToDate (2023 , 2 , 23) ,
            convertCalendarToTime (15 , 30,00  ) ,
            convertCalendarToTime (16 , 00,00  )) ;
    public Appointment appointment4 = new Appointment("non reason 4"  ,
            "no comments 4" ,
            true ,
            this.convertCalendarToDate (2023 , 2 , 23) ,
            convertCalendarToTime (16 , 00,00  ) ,
            convertCalendarToTime (16 , 30,00  )) ;



    public Account account1 = new Account(  "Belhsen" , "bachouch", 10820305,  55775085 ,
            this.convertCalendarToDate (1996 , 6 , 18)
            ,"belhsenbachouch@yahoo.fr",Gender.male
    , stateRegion.Zaghouan, "El-fahs", 1140,"Qartier Saada");


    public TimeOff TimeOff_Pause = new TimeOff("Pause", null, this.convertCalendarToTime (12 , 0,0 ),
            null, this.convertCalendarToTime (13 , 0,0 ));
    public TimeOff TimeOff_horstravail = new TimeOff("horstravail", null, this.convertCalendarToTime (17 , 0,0 ),
            null, this.convertCalendarToTime (8 , 0,0 ));

    public TimeOff TimeOff_evenement = new TimeOff("evenement", this.convertCalendarToDate (2023 , 2 , 23) ,
           null,
            this.convertCalendarToDate (2023 , 2 , 23) , null);

    public TimeOff TimeOff_convre_feu = new TimeOff("convre feu", this.convertCalendarToDate (2023 , 4 , 23) ,
            this.convertCalendarToTime (18 , 0,0 ),
            this.convertCalendarToDate (2023 , 4 , 30) ,
            this.convertCalendarToTime (6 , 0,0 ));
}