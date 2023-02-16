package tn.esprit.Controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Interface.IAdressService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/adress")
public class AdressController {

    IAdressService adressService;

    @PostMapping("add")
    public Adress addAdress(@RequestBody Adress adress){
        return adressService.addAdress(adress);
    }
    @PutMapping("modify")
    public Adress modifyAdress(@RequestBody Adress adress){
        return adressService.modifyAdress(adress);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        adressService.deleteAdresse(id);
    }
    @GetMapping("getAdress/{id}")
    public Adress getAdressById(@PathVariable("id") Long id){
        return adressService.getAdressById(id);
    }
    @GetMapping("getAllAdress")
    public List<Adress> getAdressById(){
        return adressService.getAllAdresses();
    }
}
