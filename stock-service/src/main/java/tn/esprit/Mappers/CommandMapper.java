package tn.esprit.Mappers;

import tn.esprit.Entity.Command;
import tn.esprit.dto.CommandDto;

public class CommandMapper {
    public static CommandDto mapToDo(Command command){
        CommandDto commandDto=CommandDto.builder()
                .id(command.getId())
                //.date(command.getDate())
                .notice(command.getNotice())
                .build();
        return commandDto;
    }
    public static Command mapToEntity(CommandDto commandDto){
        Command command=Command.builder()
                .id(commandDto.getId())
                //.date(commandDto.getDate())
                .notice(commandDto.getNotice())
                .build();

        return command;
    }
}
