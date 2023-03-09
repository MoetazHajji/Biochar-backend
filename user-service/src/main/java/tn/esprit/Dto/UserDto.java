package tn.esprit.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Roles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    long id;
    String username;
    String password;
    Roles roles;
    String permissions;
    boolean isEnabled;
    String code;
}
