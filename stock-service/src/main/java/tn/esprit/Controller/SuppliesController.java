package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Supplier;
import tn.esprit.Entity.Supplies;
import tn.esprit.Interface.ISupplierService;
import tn.esprit.Interface.ISuppliesService;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Supplies Management")
@RequestMapping("/supplies")
public class SuppliesController {
    ISuppliesService suppliesService;

    @Operation(description = "Create new Supplie")
    @PostMapping("add")
    public Supplies addSupplies(@RequestBody Supplies supplies){
        return suppliesService.addSupplie(supplies);
    }

    @Operation(description = "Modify Supplie")
    @PutMapping("modify")
    public Supplies modifySupplies(@RequestBody Supplies supplies){
        return suppliesService.modifySupplie(supplies);
    }

    @Operation(description = "Delete Supplie")
    @DeleteMapping("delete/{id}")
    public void deleteSupplies(@PathVariable("id") Long id){
        suppliesService.deleteSupplie(id);
    }

    @Operation(description = "Retreive Supplie by ID")
    @GetMapping("getAdress/{id}")
    public Supplies getSuppliesById(@PathVariable("id") Long id){
        return suppliesService.getSupplieById(id);
    }

    @Operation(description = "Retreive all Supplies")
    @GetMapping("getAllAdress")
    public List<Supplies> getAllSupplies(){
        return suppliesService.getAllSupplies();
    }
}
