package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Services.IGenericCRUD;
import tn.esprit.Services.IUserService;

import java.util.List;


@RestController
@RequestMapping("/timeoff")
public class TimeOffController {

    private IGenericCRUD<TimeOff> iTimeOffService;
    @Autowired
    public TimeOffController(@Qualifier("TimeOff") IGenericCRUD<TimeOff> iTimeOffService){
        this.iTimeOffService = iTimeOffService;}

    @GetMapping
    public List<TimeOff> SelectAll () {return  iTimeOffService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<TimeOff> SelectBy (@PathVariable int id) {return ResponseEntity.ok( iTimeOffService.SelectBy ( id)) ;}
    @PostMapping()
    public ResponseEntity<TimeOff> Insert( @RequestBody TimeOff user) {  return  ResponseEntity.ok( iTimeOffService.Insert(   user ));}

    @PutMapping
    public  ResponseEntity<TimeOff> update( @RequestBody TimeOff user){return ResponseEntity.ok( iTimeOffService.update(  user));}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){   iTimeOffService.delete( id ); return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
    @DeleteMapping("/all")
    public  ResponseEntity<HttpStatus> deleteAll(  ){   iTimeOffService.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

}
