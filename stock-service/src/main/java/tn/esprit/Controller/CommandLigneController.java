package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.CommandLigne;
import tn.esprit.Interface.ICommandLigneService;

@RestController
@RequestMapping("commandLigne")
@AllArgsConstructor
public class CommandLigneController {
    ICommandLigneService ligneService;

    @PostMapping("addLigneAndProduct/{id}")
    public CommandLigne AddLigneAndAssign(@RequestBody CommandLigne ligne,@PathVariable("id") Long idProd){
        return ligneService.AddLigneAndAssign(ligne,idProd);
    }
}
