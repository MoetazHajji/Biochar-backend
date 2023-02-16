package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Supplier;
import tn.esprit.Interface.ISupplierService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    ISupplierService supplierService;

    @PostMapping("add")
    public Supplier addAdress(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }
    @PutMapping("modify")
    public Supplier modifyAdress(@RequestBody Supplier supplier){
        return supplierService.modifySupplier(supplier);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        supplierService.deleteSupplier(id);
    }
    @GetMapping("getAdress/{id}")
    public Supplier getAdressById(@PathVariable("id") Long id){
        return supplierService.getSupplierById(id);
    }
    @GetMapping("getAllAdress")
    public List<Supplier> getAdressById(){
        return supplierService.getAllSuppliers();
    }
}
