package tn.esprit.appRunners;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import tn.esprit.Entitys.*;
import tn.esprit.Models.Msg;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AppointmentRepository;
import tn.esprit.Repositorys.TimeOffRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Services.*;

import javax.mail.MessagingException;
import javax.persistence.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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




    private void reset( )  {

        //newFile("C:/Worker/esprit/PI/recover-file/v9-with-JWT/Biochar-backend/user-service/src/main/resources/folder","ex.txt","ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");

        //   newFile("./user-serbvice/src/main/resources/folder","ex.json","{\"id\":1}");




    /*    try {
//          iEmailSenderService.  SendEmailWithHtml(
//                    new Msg("Forget Password",
//                        "belhsenbachouch97@gmail.com",
//                             iFileService.Edit_forgotPasswordPage("belhsen","852-272-756-732","http://locahost:8080","http://locahost:8080")
//                    ));
//           iEmailSenderService.  SendEmailWithHtml(
//                    new Msg("Forget Password",
//                           "belhsenbachouch97@gmail.com",
//                            iFileService.Edit_ConfirmMailPage("belhsen","http://locahost:8080/852-272-756-732","http://locahost:8080")
//                   ));
//           iEmailSenderService.  SendEmailWithHtml(
//                    new Msg("Forget Password",
//                            "belhsenbachouch97@gmail.com",
//                            iFileService.Edit_ReminderAppointementPage("belhsen",
//                                    new Appointment("non reason 1"  , "no comments 1" ,false ,LocalDate.now () ,
//                                            LocalTime.of (15 , 00,00  ) ,
//                                           LocalTime.of  (15 , 30,00  )),
//                                    "http://locahost:8080")
//                    ));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/




//LocalDate.now().plusDays(1)
        //   iAppointementService.sendMailSuggestionForUser(  LocalDate.of(2023, Month.MARCH, 11),
        //          LocalDate.of(2023, Month.MARCH, 14));

       /* iAppointementService.sendMailSuggestionForUser(  LocalDate.of(2023, Month.MARCH, 7),
                LocalDate.of(2023, Month.MARCH, 10));*/

    }

    @Autowired
    IAppointementService iAppointementService ;

    @Autowired
    IEmailSenderService iEmailSenderService;
    @Autowired
    IFileService iFileService;

    @Autowired
    private ResourceLoader resourceLoader;

    public String newFile(String path, String nameFile, String data){
//      File file = new File("D:/home/site/wwwroot/testfile");

        try {
            File file = new File( path );
            file.mkdirs();
//          BufferedWriter writer = new BufferedWriter( new FileWriter("D:/home/site/wwwroot/testfile/cout.txt") );
            BufferedWriter writer = new BufferedWriter( new FileWriter(path+"/"+ nameFile ) );
            writer.write( data );
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //  throw new tn.esprit.exception.IOException("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }

        return "hello world";
    }



   /* public User user1 = new User(  "Chief_Service" , "0000" , Roles.Chief_Service,"ACCESS_TEST_Receptionist"     );
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
            this.convertCalendarToTime (6 , 0,0 ));*/
}
// byte[] bytes = Files.readAllBytes(input.toPath());
          /*  iEmailSenderService.SendSimpleEmail( new Msg("Forget Password",
                    "belhsenbachouch97@gmail.com",    Jsoup.parse(input,  "UTF-8").html() ));*/
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

/*
try {//https://zetcode.com/java/jsoup/
    File input = ResourceUtils.getFile("./user-service/src/main/resources/folder/forgot_password.html");
    Document doc = Jsoup.parse(input,  "UTF-8");
    Element pag = doc.getElementById("forget-password-user-spring");

    //System.out.println( pag.data().getBytes().toString());
  pag.attr("href", "http://baeldung.com");
            //  pag.html("<a  href=\"www.exampSSSSSSSSle.com\" style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#101;border-radius:4px;width:auto;border-top:1px solid #101;font-weight:undefined;border-right:1px solid #101;border-bottom:1px solid #101;border-left:1px solid #101;padding-top:5px;padding-bottom:5px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span style=\"word-break: break-word; line-height: 32px;\">Reset Password</span></span></a>");
   // doc.getElementById("forget-password-user-spring").attr("href", "http://baeldung.com");

    //String htmlString = "<html><head><title>My title</title></head><body>Body content</body></html>";
   // Document doc = Jsoup.parse(htmlString);
  //   PrintWriter writer = new PrintWriter(input,"UTF-8");
 //    writer.write(doc.html() ) ;
 //   writer.flush();
//   writer.close();
}
catch( Exception e ){
System.out.println(e.getMessage());
}*/










// File directory = new File("./");
// File directory = new File("./"+"user-service/src/main/resources/folder");
//  System.out.println(directory.getAbsolutePath());


       /* String filePath = "./user-service/src/main/resources/folder/ex.txt"; // Relative file path
        String fileContent = null;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(fileContent);*/




     /*   String resourcePath = "classpath:folder/ex.txt";
        String resourceDirectory = null;
        try {
            resourceDirectory = getResourceDirectory(resourcePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Resource directory: " + resourceDirectory);*/


