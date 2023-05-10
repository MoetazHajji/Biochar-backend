package tn.esprit.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.Roles;
import tn.esprit.Entitys.User;
import tn.esprit.Services.IGenericCRUD;
import tn.esprit.Services.IUserService;

import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/User")
public class UserController {
    private IUserService iUserService;
    @Autowired
    public UserController(@Qualifier("User") IUserService iUserService){this.iUserService = iUserService;}

    @GetMapping
    public List<UserDto> SelectAll () {return  iUserService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<UserDto> SelectBy (@PathVariable int id) {return ResponseEntity.ok(  iUserService.SelectBy ( id)) ;}

     @PostMapping
     public UserDto Insert( @RequestBody UserDto user) {return  iUserService.Insert(   user);}

    @PutMapping
    public  ResponseEntity<UserDto> update( @RequestBody UserDto user){return  ResponseEntity.ok( iUserService.update(  user));}

    @PutMapping("sendMailCode/{email}")
    String sendMailCode_ForgotPassword(@PathVariable("email")  String email){
        return  iUserService.sendMailCode_ForgotPassword( email );}

    @PutMapping("confirmationCode/{code}/{password}")
    boolean confirmationCode(@PathVariable("code") String code,@PathVariable("password")  String password){
        return iUserService.confirmationCode( code,  password);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){  iUserService.delete( id ); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }

    @PutMapping("updateRole/{id_user}/{role}")
    ResponseEntity<UserDto>   updateRole(@PathVariable("id_user") long id_user,@PathVariable("role") Roles role){
        return  ResponseEntity.ok(   iUserService.updateRole( id_user,  role));
    }







}



/* ex post http://localhost:8085/biochar/User
{
     "username":"belhsen97",
     "password":"97747369",
     "roles":"Doctor",
     "permissions":"admin"
}
 */