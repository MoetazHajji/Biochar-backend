package tn.esprit.Controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Interface.IAdressService;

@AllArgsConstructor
@RestController
@RequestMapping("/adress")
public class AdressController {

    IAdressService adressService;

    @PostMapping("add")
    public Adress addAdress(@RequestBody Adress adress){
        return adressService.addAdress(adress);
    }
    @PostMapping("add")
    public Adress aAdress(@RequestBody Adress adress){
        return adressService.addAdress(adress);
    }
}
