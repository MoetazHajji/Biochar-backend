package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Command;
import tn.esprit.Interface.ICommandService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/command")
public class CommandController {
    ICommandService commandService;

    @PostMapping("add")
    public Command addAdress(@RequestBody Command command){
        return commandService.addCommand(command);
    }
    @PutMapping("modify")
    public Command modifyAdress(@RequestBody Command command){
        return commandService.modifyCommand(command);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        commandService.deleteCommand(id);
    }
    @GetMapping("getAdress/{id}")
    public Command getAdressById(@PathVariable("id") Long id){
        return commandService.getCommandById(id);
    }
    @GetMapping("getAllAdress")
    public List<Command> getAdressById(){
        return commandService.getAllCommands();
    }
}
