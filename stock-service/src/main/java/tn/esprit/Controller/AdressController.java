package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Interface.IAdressService;

import java.util.List;
import java.util.Set;

@Tag(name = "Adress Management")
@AllArgsConstructor
@RestController
@RequestMapping("/adress")
//@CrossOrigin(origins = "http://localhost:4200")
public class AdressController {

    IAdressService adressService;

    @Operation(description = "Add new adress")
    @PostMapping("add")
    public Adress addAdress(@RequestBody Adress adress){
        return adressService.addAdress(adress);
    }
    @Operation(description = "Modify adress")
    @PutMapping("modify")
    public Adress modifyAdress(@RequestBody Adress adress){
        return adressService.modifyAdress(adress);
    }

    @Operation(description = "Delete adress")
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        adressService.deleteAdresse(id);
    }

    @Operation(description = "Retreive adress by id")
    @GetMapping("getAdress/{id}")
    public Adress getAdressById(@PathVariable("id") Long id){
        return adressService.getAdressById(id);
    }

    @Operation(description = "Retreive all adresses")
    @GetMapping("getAllAdress")
    public Set<Adress> getAllAdress(){
        return adressService.getAllAdresses();
    }
}
