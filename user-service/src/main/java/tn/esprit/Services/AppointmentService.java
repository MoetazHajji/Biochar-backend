package tn.esprit.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.AppointmentStatus;
import tn.esprit.Entitys.User;
import tn.esprit.Mappers.AppointmentMapper;
import tn.esprit.Models.AuthenticationResponse;
import tn.esprit.Models.AuthenticationStatus;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AppointmentRepository;
import tn.esprit.Repositorys.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service("Appointment")
public class AppointmentService   implements IAppointementService {
    private AppointmentRepository appointmentRepository;
    private AccountRepository accountRepository;
    private ITimeOffService iTimeOffService;
    private  UserRepository userRepository;
    private IFileService iFileService;
    @Autowired // Methode 2
    public AppointmentService(AppointmentRepository appointmentRepository,
                              @Qualifier("TimeOff") ITimeOffService iTimeOffService ,
                              AccountRepository accountRepository,
                              UserRepository userRepository,
                              IFileService iFileService
    )
    {  this.appointmentRepository = appointmentRepository;
        this.iTimeOffService = iTimeOffService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.iFileService = iFileService;
    }

    @Override
    public List<AppointmentDto> SelectAll() {  return appointmentRepository.findAll().
            stream().map(appointment -> AppointmentMapper.mapToDto(appointment)).collect(Collectors.toList()); }

    @Override
    public  AppointmentDto  SelectBy(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null)  ;
        return AppointmentMapper.mapToDto(appointment);
    }

    @Override
    public AppointmentDto Insert(AppointmentDto object) {
        object.setCreatedAt(LocalDateTime.now());
        object.setAppointmentStatus(this.Verify( AppointmentMapper.mapToEntity( object )  ));
        Appointment appointment = AppointmentMapper.mapToEntity(object);
        return AppointmentMapper.mapToDto( appointmentRepository.save(appointment));
    }


    @Override
    @Transactional
    public  AppointmentDto  update(AppointmentDto object) {
        Appointment appointment = appointmentRepository.findById(object.getId()).orElse(null)  ;
        object.setAppointmentStatus(this.Verify( AppointmentMapper.mapToEntity( object )  ));
        appointment.setReason(object.getReason());
        //appointment.setDate_sending(object.getDate_sending());
        appointment.setComments(object.getComments());
        appointment.setFirstVisit(object.isFirstVisit());
        appointmentRepository.save(appointment);
        return  AppointmentMapper.mapToDto(appointment) ;
    }
    /* @Transactional
     public  AppointmentDto  updateAppointmentByAccount(AppointmentDto object,long idAccount) {
         Account account = accountRepository.findById(idAccount).orElse(null)  ;
         Appointment appointment = AppointmentMapper.mapToEntity(object);
         Appointment new_update_appointment = appointmentRepository.findById(appointment.getId()).orElse(null)  ;
         object.setAppointmentStatus(this.Verify( AppointmentMapper.mapToEntity( object )  ));
         appointment.setReason(object.getReason());
         //appointment.setDate_sending(object.getDate_sending());
         appointment.setComments(object.getComments());
         appointment.set_first_visit(object.is_first_visit());
         appointmentRepository.save(appointment);
         return  AppointmentMapper.mapToDto(appointment) ;
     }*/
    @Override
    public boolean delete(long id) {
        boolean deleted = false;
        Appointment appointment = appointmentRepository.findById(id).orElse(null) ;
        if (appointment != null ) {
            appointmentRepository.delete(appointment);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        return null;
    }



    @Transactional
    @Override
    public AppointmentDto  assignAppointmentToAccount(Long idAppointment, Long idAccount)
    {
        Appointment appointment = appointmentRepository.findById(idAppointment).orElse(null);
        Account account = accountRepository.findById(idAccount).orElse(null);
        appointment.setAccount(account);
        return AppointmentMapper.mapToDto(appointmentRepository.save(appointment));
    }

    @Transactional
    @Override
    public AppointmentDto  AddAppointmentAndAssignToAccount( Long idAccount , AppointmentDto appointmentDto )
    {
        Account account = accountRepository.findById(idAccount).orElse(null);
        Appointment appt =  AppointmentMapper.mapToEntity( appointmentDto );
        appt.setAppointmentStatus(this.Verify( appt  ));
        appt.setCreatedAt(LocalDateTime.now());
        appt =  appointmentRepository.save(appt);
        appt.setAccount(account);
        return AppointmentMapper.mapToDto(appointmentRepository.save(appt));
    }

@Override
    public AppointmentStatus Verify( Appointment appointment ) {
        AppointmentStatus appointmentStatus =  AppointmentStatus.Available;
        if (!iTimeOffService.verify (appointment.getAppointmentDate() ,appointment.getAppointmentStartTime(), appointment.getAppointmentEndTime()))
        {appointmentStatus =  AppointmentStatus.Booked; }
        if (  appointmentRepository. isInBetweenTwoTimeAndDate(
                appointment.getAppointmentDate(),
                appointment.getAppointmentStartTime() ,
                appointment.getAppointmentEndTime())){ appointmentStatus =  AppointmentStatus.Booked; }
    LocalDateTime dateTimeApptStart = appointment.getAppointmentDate().atTime(appointment.getAppointmentEndTime());
    if (  ( LocalDateTime.now().compareTo(dateTimeApptStart) > 0)  ){ appointmentStatus =  AppointmentStatus.Booked; }
    return appointmentStatus;
    }








    @Override              // LocalDate.now().plusDays(1)
    public void sendMailSuggestionForUser(LocalDate StartDate ,  LocalDate LestDate , Appointment appt ) {
        LocalTime startWorkTime = LocalTime.of(8,0);
        LocalTime endWorkTime = LocalTime.of(16,0);
        boolean NotReturn = true;

        boolean dat = true;

        while (  (StartDate.compareTo(LestDate) <= 0)  && NotReturn )   // StartDate.isBefore(LestDate)
        {
            LocalTime startSearchTime = startWorkTime;
            LocalTime endSearchTime = startWorkTime.plusHours(1);
            while   (!startWorkTime.isAfter(endWorkTime) && NotReturn && dat)
            {

                if (
                        !    appointmentRepository. isInBetweenTwoTimeAndDate(StartDate ,
                                startSearchTime,
                                endSearchTime )
                )
                {
                    System.out.println("We founded  !!!!!!!!!!!!!!!!!");
                    System.out.println(StartDate.toString() +" "+  startSearchTime.toString()  +" "+  endSearchTime.toString() );
                    //iFileService.Edit_sendMailSuggestionPage ( appt.getAccount().getUser().getUsername(),
                    //         appt, String link , String pageHomeLink )


                    NotReturn= false;
                }
                startSearchTime = endSearchTime;
                endSearchTime = endSearchTime.plusHours(1);
                startWorkTime.plusHours(1);

            }

            StartDate.plusDays(1);
        }

    }
}