package tn.esprit.Mappers;

import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.Permission;
import tn.esprit.Entitys.Roles;
import tn.esprit.Entitys.TypePermission;
import tn.esprit.Entitys.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

  static   List<Permission>  listPermissions  = new ArrayList<Permission>(){{
        add( new Permission(1, TypePermission.Front,Roles.Chief_Service,"/*") );
        add( new Permission(2, TypePermission.Front,Roles.Patient,"/analysisPatient") );
        add( new Permission(2, TypePermission.Front,Roles.Patient,"/admin/account/edit-detailled/*") );
        add( new Permission(3, TypePermission.Front,Roles.Patient,"/admin/appointment/*") );
        add( new Permission(4, TypePermission.Front,Roles.Receptionist,"/admin/account/edit-detailled/*") );
        add( new Permission(5, TypePermission.Front,Roles.Doctor,"/admin/account/edit-detailled/*") );
        add( new Permission(6, TypePermission.Front,Roles.Biologist,"/admin/account/edit-detailled/*") );
        add( new Permission(7, TypePermission.Front,Roles.Intern,"/admin/account/edit-detailled/*") );
    }};

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
        List<String> ListPathPermission = new ArrayList<String>();
        if ( user != null ){
         ListPathPermission = listPermissions.stream()
                .filter(p -> p.getRoles() == user.getRoles())
                 .map(p -> p.getPath())
                .collect(Collectors.toList());}
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                //.password(user.getPassword())
                .roles(user.getRoles())
                .listPermissions(ListPathPermission)
                //.listPermissions(ListPermissions( ))
                .enabled(user.isEnabled())
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
