package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.User;
import tn.esprit.Services.IGenericCRUD;

import java.util.List;

@Controller
@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
    private IGenericCRUD<Appointment> IAppointmentService;
    @Autowired
    public AppointmentController(@Qualifier("Appointment") IGenericCRUD<Appointment> IAppointmentService){this.IAppointmentService = IAppointmentService;}

    @GetMapping
    public List<Appointment> SelectAll () {return  IAppointmentService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<Appointment> SelectBy (@PathVariable int id) {return  IAppointmentService.SelectBy ( id) ;}

    @PostMapping
    public Appointment Insert( @RequestBody Appointment appointment) {return  IAppointmentService.Insert(   appointment);}

    @PutMapping
    public  ResponseEntity<Appointment> update( @RequestBody Appointment appointment){return  IAppointmentService.update(  appointment);}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  IAppointmentService.delete( id ); }
}
