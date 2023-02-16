package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Command;
import tn.esprit.Interface.ICommandService;
import tn.esprit.Repository.ICommandRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandService implements ICommandService {

    ICommandRepository commandRepository;
    @Override
    public Command addCommand(Command command) {
        return commandRepository.save(command);
    }

    @Override
    public Command modifyCommand(Command command) {
        return commandRepository.save(command);
    }

    @Override
    public void deleteCommand(Long id) {
            commandRepository.deleteById(id);
    }

    @Override
    public Command getCommandById(Long id) {
        return commandRepository.findById(id).orElse(null);
    }

    @Override
    public List<Command> getAllCommands() {
        List<Command> commandList =new ArrayList<>();
        commandRepository.findAll().forEach(commandList::add);
        return commandList;
    }
}
