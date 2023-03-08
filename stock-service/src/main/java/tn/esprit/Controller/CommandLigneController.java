package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.CommandLigne;
import tn.esprit.Interface.ICommandLigneService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("commandLigne")
@AllArgsConstructor
public class CommandLigneController {
    ICommandLigneService ligneService;

    @PostMapping("addLigneAndProduct/{id}")
    public CommandLigne AddLigneAndAssign(@RequestBody CommandLigne ligne,@PathVariable("id") Long idProd){
        return ligneService.AddLigneAndAssign(ligne,idProd);
    }

    @Operation(description = "Delete adress")
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        ligneService.deleteOrderLine(id);
    }

    @Operation(description = "Retreive Command Ligne by id")
    @GetMapping("getCommLigne/{id}")
    public CommandLigne getAdressById(@PathVariable("id") Long id){
        return ligneService.GetCommandById(id);
    }

    @Operation(description = "Retreive all Command Lignes")
    @GetMapping("getAllCommLignes")
    public Set<CommandLigne> getAllAdress(){
        return ligneService.getAllOrdersLine();
    }
}
