package tn.esprit.Mappers;

import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMapper {
    public static User mapToEntity(UserDto UserDto){
        return User.builder()
                .id(UserDto.getId())
                .username(UserDto.getUsername())
                .password(UserDto.getPassword())
                //.roles(UserDto.getRoles())
                //.permissions(UserDto.getPermissions())
                //.isEnabled(UserDto.isEnabled())
                .build();
    }
    public static UserDto mapToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                //.password(user.getPassword())
                .roles(user.getRoles())
                //.permissions(user.getPermissions())
                //.ListPermissions(ListPermissions(user.getPermissions() ))
                .isEnabled(user.isEnabled())
                .build();
    }
    private static List<String> ListPermissions(String permissions ){
        if (permissions == null || permissions.isEmpty())
        {
            return new ArrayList<>();
        }
        if((permissions.length() > 0)&&(permissions != null)){
            return Arrays.asList(permissions.split(","));
        }
        return new ArrayList<>();
    }
}