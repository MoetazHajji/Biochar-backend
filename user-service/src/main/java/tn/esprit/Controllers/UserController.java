package tn.esprit.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entitys.User;
import tn.esprit.Services.IGenericCRUD;

import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/User")
public class UserController {
    private IGenericCRUD<User> IUserService;
    @Autowired
    public UserController(@Qualifier("User") IGenericCRUD<User> IUserService){this.IUserService = IUserService;}

    @GetMapping
    public List<User> SelectAll () {return  IUserService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<User> SelectBy (@PathVariable int id) {return  IUserService.SelectBy ( id) ;}

    @PostMapping
    public User Insert( @RequestBody User user) {return  IUserService.Insert(   user);}

    @PutMapping
    public  ResponseEntity<User> update( @RequestBody User user){return  IUserService.update(  user);}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  IUserService.delete( id ); }

}



/* ex post http://localhost:8085/biochar/User
{
     "username":"belhsen97",
     "password":"97747369",
     "roles":"Doctor",
     "permissions":"admin",
     "isEnabled":true
}
 */