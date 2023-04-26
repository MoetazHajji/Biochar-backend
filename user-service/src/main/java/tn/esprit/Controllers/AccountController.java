package tn.esprit.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.User;
import tn.esprit.Mappers.IObjectMapperConvert;
import tn.esprit.Mappers.ObjectMapperConvert;
import tn.esprit.Models.Msg;
import tn.esprit.Services.IAccountService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/Account")
public class AccountController {
    private  IAccountService iAccountService;
    @Autowired
    IObjectMapperConvert objectMapperConvert ;

    @Autowired
    private KafkaTemplate<Object,  AccountDto > kafkaTemplateAccountDto;
    @Autowired
    public AccountController(@Qualifier("Account") IAccountService iAccountService){this.iAccountService = iAccountService;}

    @GetMapping
    public List<AccountDto> SelectAll () {
     List<AccountDto>  accountDtos = iAccountService. SelectAll () ;
        return  accountDtos;}

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> SelectBy (@PathVariable int id) {
        return ResponseEntity.ok( iAccountService.SelectBy ( id) );}

    @PostMapping
    public AccountDto Insert( @RequestBody AccountDto accountDto) {

        return  iAccountService.Insert(   accountDto);}

    @PutMapping
    public  ResponseEntity<AccountDto> update( @RequestBody AccountDto accountDto){
        return ResponseEntity.ok( iAccountService.update(  accountDto));}

    @PutMapping ("AssignUserToAccount/{idUser}/{idAccount}")
    public AccountDto assignUserToAccount(@PathVariable("idUser")  Long idUser , @PathVariable("idAccount")   Long idAccount){return  iAccountService.assignUserToAccount( idUser,  idAccount);}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){
        iAccountService.delete( id ); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
}

