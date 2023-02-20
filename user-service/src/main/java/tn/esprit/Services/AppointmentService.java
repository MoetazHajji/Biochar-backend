package tn.esprit.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.User;
import tn.esprit.Repositorys.AppointmentRepository;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;


@Service("Appointment")
public class AppointmentService   implements IGenericCRUD<Appointment> {
    private AppointmentRepository appointmentRepository;
    @Autowired // Methode 2
    public AppointmentService(AppointmentRepository appointmentRepository){  this.appointmentRepository = appointmentRepository; }

    @Override
    public List<Appointment> SelectAll() {  return appointmentRepository.findAll();  }

    @Override
    public  ResponseEntity<Appointment>  SelectBy(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok(appointment);
    }

    @Override
    public Appointment Insert(Appointment object) {
        return appointmentRepository.save(object);
    }

    @Override
    @Transactional
    public ResponseEntity<Appointment> update(Appointment object) {
        Appointment appointment = appointmentRepository.findById(object.getId()).orElse(null)  ;
        appointment.setReason(object.getReason());
        appointment.setDate_sending(object.getDate_sending());
        appointment.setComments(object.getComments());
        appointment.set_first_visit(object.is_first_visit());
        appointmentRepository.save(appointment);
        return ResponseEntity.ok(appointment);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null) ;
        appointmentRepository.delete(appointment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
