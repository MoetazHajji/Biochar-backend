package tn.esprit.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
import tn.esprit.Mappers.AccountMapper;
import tn.esprit.Mappers.AppointmentMapper;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AppointmentRepository;

import javax.persistence.Column;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("Appointment")
public class AppointmentService   implements IAppointementService {
    private AppointmentRepository appointmentRepository;
    private AccountRepository accountRepository;
    private ITimeOffService iTimeOffService;
    @Autowired // Methode 2
    public AppointmentService(AppointmentRepository appointmentRepository,
                              @Qualifier("TimeOff") ITimeOffService iTimeOffService ,
                              AccountRepository accountRepository)
    {  this.appointmentRepository = appointmentRepository;  this.iTimeOffService = iTimeOffService; this.accountRepository = accountRepository; }

    @Override
    public List<AppointmentDto> SelectAll() {  return appointmentRepository.findAll().
            stream().map(appointment -> AppointmentMapper.mapToDto(appointment)).collect(Collectors.toList()); }

    @Override
    public  ResponseEntity<AppointmentDto>  SelectBy(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok(AppointmentMapper.mapToDto(appointment));
    }

    @Override
    public AppointmentDto Insert(AppointmentDto object) {
         object.setCreatedAt( new Date(System.currentTimeMillis()));
       object.setAppointmentStatus(this.Verify( AppointmentMapper.mapToEntity( object )  ));
        Appointment appointment = AppointmentMapper.mapToEntity(object);
        return AppointmentMapper.mapToDto( appointmentRepository.save(appointment));
    }

    @Override
    @Transactional
    public ResponseEntity<AppointmentDto> update(AppointmentDto object) {
        Appointment appointment = appointmentRepository.findById(object.getId()).orElse(null)  ;
        object.setAppointmentStatus(this.Verify( AppointmentMapper.mapToEntity( object )  ));
        appointment.setReason(object.getReason());
        //appointment.setDate_sending(object.getDate_sending());
        appointment.setComments(object.getComments());
        appointment.set_first_visit(object.is_first_visit());
        appointmentRepository.save(appointment);
        return ResponseEntity.ok(AppointmentMapper.mapToDto(appointment));
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null) ;
        appointmentRepository.delete(appointment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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



    private AppointmentStatus Verify( Appointment appointment ) {
        AppointmentStatus appointmentStatus = null ;
        if (iTimeOffService.verify (appointment.getAppointmentDate() ,appointment.getAppointmentStartTime(), appointment.getAppointmentEndTime()))
        {appointmentStatus =  AppointmentStatus.Booked; }
        else { appointmentStatus =  AppointmentStatus.Available;}

       if (  appointmentRepository. isInBetweenTwoTimeAndDate(
               appointment.getAppointmentStartTime() ,
               appointment.getAppointmentEndTime())){ appointmentStatus =  AppointmentStatus.Booked; }
       else {appointmentStatus =  AppointmentStatus.Available;}
        return appointmentStatus;
    }

}
