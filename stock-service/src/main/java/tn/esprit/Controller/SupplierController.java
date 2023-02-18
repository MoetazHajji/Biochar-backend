package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Supplier;
import tn.esprit.Interface.ISupplierService;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Supplier Management")
@RequestMapping("/supplier")
public class SupplierController {
    ISupplierService supplierService;

    @Operation(description = "Create new Supplier")
    @PostMapping("add")
    public Supplier addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @Operation(description = "Modify Supplier")
    @PutMapping("modify")
    public Supplier modifySupplier(@RequestBody Supplier supplier){
        return supplierService.modifySupplier(supplier);
    }

    @Operation(description = "Delete Supplier")
    @DeleteMapping("delete/{id}")
    public void deleteSupplier(@PathVariable("id") Long id){
        supplierService.deleteSupplier(id);
    }

    @Operation(description = "Retreive Supplier by ID")
    @GetMapping("getAdress/{id}")
    public Supplier getSupplierById(@PathVariable("id") Long id){
        return supplierService.getSupplierById(id);
    }
    @Operation(description = "Retreive All Suppliers")
    @GetMapping("getAllAdress")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSuppliers();
    }
}
