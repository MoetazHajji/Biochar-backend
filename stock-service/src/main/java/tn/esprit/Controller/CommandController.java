package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Command;
import tn.esprit.Interface.ICommandService;

import java.util.List;

@Tag(name = "Command Management")
@AllArgsConstructor
@RestController
@RequestMapping("/command")
public class CommandController {
    ICommandService commandService;

    @Operation(description = "Add new Command")
    @PostMapping("add")
    public Command addCommand(@RequestBody Command command){
        return commandService.addCommand(command);
    }

    @Operation(description = "Modify Command")
    @PutMapping("modify")
    public Command modifyCommand(@RequestBody Command command){
        return commandService.modifyCommand(command);
    }

    @Operation(description = "Delete Command")
    @DeleteMapping("delete/{id}")
    public void deleteCommand(@PathVariable("id") Long id){
        commandService.deleteCommand(id);
    }

    @Operation(description = "Retreive Command by Id")
    @GetMapping("getAdress/{id}")
    public Command getCommandById(@PathVariable("id") Long id){
        return commandService.getCommandById(id);
    }

    @Operation(description = "Retreive all Commands")
    @GetMapping("getAllAdress")
    public List<Command> getAllCommand(){
        return commandService.getAllCommands();
    }
}
