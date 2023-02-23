package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Services.IAppointementService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
    private IAppointementService iAppointmentService;
    @Autowired
    public AppointmentController(@Qualifier("Appointment") IAppointementService iAppointmentService){this.iAppointmentService = iAppointmentService;}
    @GetMapping
    public List<AppointmentDto> SelectAll () {return  iAppointmentService. SelectAll () ;}
    @GetMapping("{id}")
    public ResponseEntity<AppointmentDto> SelectBy (@PathVariable int id) {return  iAppointmentService.SelectBy ( id) ;}
    @PostMapping
    public AppointmentDto Insert(@RequestBody AppointmentDto appointmentDto) {return  iAppointmentService.Insert(   appointmentDto);}
    @PutMapping
    public  ResponseEntity<AppointmentDto> update( @RequestBody AppointmentDto appointmentDto){return  iAppointmentService.update(  appointmentDto);}
    @PutMapping ("assignAppointmentToAccount/{idAppointment}/{idAccount}")
    public AppointmentDto  assignAppointmentToAccount(@PathVariable("idAppointment")  Long idAppointment, @PathVariable("idAccount")  Long idAccount){
        return   iAppointmentService.assignAppointmentToAccount( idAppointment,  idAccount);
    }
    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  iAppointmentService.delete( id ); }
}
