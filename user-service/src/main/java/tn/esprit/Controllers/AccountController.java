package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Services.IGenericCRUD;

import java.util.List;

@Controller
@RestController
@RequestMapping("/Account")
public class AccountController {
    private IGenericCRUD<Account> IAccountService;
    @Autowired
    public AccountController(@Qualifier("Account") IGenericCRUD<Account> IAccountService){this.IAccountService = IAccountService;}

    @GetMapping
    public List<Account> SelectAll () {return  IAccountService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<Account> SelectBy (@PathVariable int id) {return  IAccountService.SelectBy ( id) ;}

    @PostMapping
    public Account Insert( @RequestBody Account account) {return  IAccountService.Insert(   account);}

    @PutMapping
    public  ResponseEntity<Account> update( @RequestBody Account account){return  IAccountService.update(  account);}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  IAccountService.delete( id ); }
}


/*
{
     "firstname":"belhsen",
     "lastname":"bachouch",
     "cin":10820305,
     "phone":55775085,
     "dateOfBirth":"1996-06-18",
     "dateCreation":"2023-02-16",
     "email":"belhsenbachouch@yahoo.fr",
     "gender":"male"
}
* */