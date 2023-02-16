package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Supplier;
import tn.esprit.Entity.Supplies;
import tn.esprit.Interface.ISupplierService;
import tn.esprit.Interface.ISuppliesService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/supplies")
public class SuppliesController {
    ISuppliesService suppliesService;

    @PostMapping("add")
    public Supplies addAdress(@RequestBody Supplies supplies){
        return suppliesService.addSupplie(supplies);
    }
    @PutMapping("modify")
    public Supplies modifyAdress(@RequestBody Supplies supplies){
        return suppliesService.modifySupplie(supplies);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        suppliesService.deleteSupplie(id);
    }
    @GetMapping("getAdress/{id}")
    public Supplies getAdressById(@PathVariable("id") Long id){
        return suppliesService.getSupplieById(id);
    }
    @GetMapping("getAllAdress")
    public List<Supplies> getAdressById(){
        return suppliesService.getAllSupplies();
    }
}
