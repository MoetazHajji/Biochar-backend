package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Services.IAccountService;
import tn.esprit.Services.IGenericCRUD;

import java.util.List;

@Controller
@RestController
@RequestMapping("/Account")
public class AccountController {
    private  IAccountService iAccountService;
    @Autowired
    public AccountController(@Qualifier("Account") IAccountService iAccountService){this.iAccountService = iAccountService;}

    @GetMapping
    public List<AccountDto> SelectAll () {return  iAccountService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> SelectBy (@PathVariable int id) {return ResponseEntity.ok( iAccountService.SelectBy ( id) );}

    //@PostMapping
    //public AccountDto Insert( @RequestBody AccountDto accountDto) {return  iAccountService.Insert(   accountDto);}

    @PutMapping
    public  ResponseEntity<AccountDto> update( @RequestBody AccountDto accountDto){return ResponseEntity.ok( iAccountService.update(  accountDto));}

    @PutMapping ("AssignUserToAccount/{idUser}/{idAccount}")
    public AccountDto assignUserToAccount(@PathVariable("idUser")  Long idUser , @PathVariable("idAccount")   Long idAccount){return  iAccountService.assignUserToAccount( idUser,  idAccount);}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){   iAccountService.delete( id ); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
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