package tn.esprit.appRunners;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.*;
import tn.esprit.Mappers.IObjectMapperConvert;
import tn.esprit.Models.Msg;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AppointmentRepository;
import tn.esprit.Repositorys.TimeOffRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Services.*;
import tn.esprit.exception.ErrorDetails;

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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component
public class bean_ResetData implements CommandLineRunner {
    //    @Autowired
//    private KafkaTemplate<Object, AccountDto> kafkaTemplateAccountDto;
//    @Autowired
//    IObjectMapperConvert objectMapperConvert ;
    @Override
    public void run(String... args) throws Exception {
        log.info("Bean One of Reset Data  run method Started !!" );
        // this.reset( );

//        kafkaTemplateAccountDto.  send("topic-service-user-account-insert",  accountDto  );
//        kafkaTemplateAccountDto.flush();

    }





//    @KafkaListener(topics = "topic-service-user-account-insert", groupId = "topic-service-user-account-groupe-1", containerFactory = "StringKafkaListenerContainerFactory")
//    public void consume_insert  (String payload)
//    {
//
//        AccountDto accountDto = null ;
//        System.out.println("topic-service-account-selectAll :  = " + payload);
//        try {  accountDto   = (AccountDto) objectMapperConvert.convertToObject(payload,AccountDto.class);
//        }
//        catch (JsonProcessingException e) {
//            System.out.println("ERROR topic-service-account-selectAll :  = " + e.getMessage());
//        }
//        System.out.println("KafkaConsumer consume : topic-service-account-selectAll = " +  accountDto );
//    }


    AccountDto accountDto = new AccountDto( LocalDateTime.now(),
            "firstname" ,"lastname" ,10820305,55775085,
            LocalDate.now() , LocalDate.now() , "belhsenbachouch@gmail.com","photo",Gender.male,
            StateRegion.Monastir, "cite" , 1140  , "adresse" , Roles.Patient ,
            Team.Team_A ,
            Shift.Afternoon/*,
            new HashSet<>() */);
    Account account =  new Account("firstname" ,"lastname" ,10820305,55775085,
            LocalDate.now(), "belhsenbachouch@gmail.com", Gender.male,StateRegion.Monastir, "cite" , 1140  , "adresse" );
    User user =   new User( "username",  "password" , Roles.Patient);
    Appointment appt = new Appointment( "reason",  "comments",  false, LocalDate.now(),
            LocalTime.now(), LocalTime.now());

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    private void reset( )  {
        account.setUser(user);
        user.setAccount(account);
        Set<Appointment> appointments = new HashSet<Appointment>(){{}} ;
        appointments.add(appt);
        account.setAppointments(appointments);
        for (Appointment appointment : appointments)
        {
            appointment.setAccount(account);
        }
        accountRepository.save(account);



        //  kafkaTemplate.send("TopicString",accountDto);
        //  kafkaTemplate.flush();

    /*  Msg msg = new Msg( "subject",  "email",  "body");
        try {
            String json = objectMapperConvert.convertToJsonString(msg);
            System.out.println("ResultingJSONstring = " + json);
            //  List<AccountDto>  accntDtos = (List<AccountDto>) objectMapperConvert.convertToObjectList(json);
            //   System.out.println("Aaaaa = " + accntDtos.size());
            Msg accntDtos = (Msg) objectMapperConvert.convertToObject(json ,Msg.class);
            System.out.println("Aaaaa = " + accntDtos.getEmail());
        } catch (JsonProcessingException e) {
            System.out.println("Erreur = "+e.getMessage());
        }
        //  kafkaTemplate.send("TopicString",accountDtos); */










        // List <Msg> msgs = new ArrayList<Msg>(){{add(msg);}};


        //  kafkaTemplate.send("TopicMsg",msg);
        //  kafkaTemplate.send("TopicString",msg);
        //message = {"id":0,    "reason":"AppointmentDtoReson"             ,"createdAt":null,"comments":null,"appointmentDate":null,
        // "appointmentStartTime":null,"appointmentEndTime":null,"appointmentStatus":null,         "_first_visit":false    }
//"AppointmentDto(id=0, reason=AppointmentDtoReson, createdAt=null, comments=null, is_first_visit=false, appointmentDate=null, appointmentStartTime=null, appointmentEndTime=null, appointmentStatus=null)"


//        ObjectMapper mapper = new ObjectMapper();//https://www.tabnine.com/blog/how-to-convert-a-java-object-into-a-json-string/
//        try {
//            mapper.registerModule(new JavaTimeModule());
//            String json = mapper.writeValueAsString(accountDtos);
//
//            System.out.println("ResultingJSONstring = " + json);
//            List<AccountDto> myList = mapper.readValue(json, new TypeReference<List<AccountDto>>() {});
//            System.out.println("Aaaaa = " + myList.get(1).getAppointments().size());
//            //kafkaTemplate.send("TopicAppointmentDto",appointmentDto);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
 /*AccountDto accountDto = new AccountDto( 1,"firstname" ,"lastname" ,10820305,55775085,
              LocalDate.now() ,  new Date() , LocalDate.now() , "belhsenbachouch@gmail.com","photo",Gender.male,
          stateRegion.Monastir, "cite" , 1140  , "adresse" , Roles.Patient , new HashSet<>() );





        ObjectMapper mapper = new ObjectMapper();//https://www.tabnine.com/blog/how-to-convert-a-java-object-into-a-json-string/
        try {
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(accountDto);

            System.out.println("ResultingJSONstring = " + json);
            AccountDto car = mapper.readValue(json, AccountDto.class);
           System.out.println("messageX = " + car.getRoles());
            //kafkaTemplate.send("TopicAppointmentDto",appointmentDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/


      /*AccountDto accountDto = new AccountDto( 1,"firstname" ,"lastname" ,10820305,55775085,
              LocalDate.now() ,  new Date() , LocalDate.now() , "belhsenbachouch@gmail.com","photo",Gender.male,
          stateRegion.Monastir, "cite" , 1140  , "adresse" , Roles.Patient , new HashSet<>() );





        ObjectMapper mapper = new ObjectMapper();//https://www.tabnine.com/blog/how-to-convert-a-java-object-into-a-json-string/
        try {
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(accountDto);

            System.out.println("ResultingJSONstring = " + json);
            AccountDto car = mapper.readValue(json, AccountDto.class);
           System.out.println("messageX = " + car.getRoles());
            //kafkaTemplate.send("TopicAppointmentDto",appointmentDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/



     /*   String   message = "{\"id\":0,\"reason\":\"AppointmentDtoReson\",\"createdAt\":1679527351490,\"comments\":null,\"appointmentDate\":[2023,3,22],\"appointmentStartTime\":[16,22,31,491000000],\"appointmentEndTime\":null,\"appointmentStatus\":null,\"firstVisit\":false}";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
           // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            AppointmentDto car = mapper.readValue(message, AppointmentDto.class);
            System.out.println("messageX = " + car.getAppointmentStartTime());
        } catch (JsonProcessingException e) {
            System.out.println("Error = " + e.getMessage());
        }*/












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


