package tn.esprit.Dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {
    long id;
    String firstname;
    String lastname;
    int cin;
    int phone;
    LocalDate dateOfBirth;
    Date dateCreation;
    LocalDate hireDate;
    String email;
    String photo;
    Gender gender;
    stateRegion state;
    String city;
    int zip_code;
    String adresse;
    Roles roles;
    //User user;
    Set<Appointment> appointments;
}



