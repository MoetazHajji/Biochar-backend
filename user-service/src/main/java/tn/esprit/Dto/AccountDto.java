package tn.esprit.Dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.Gender;
import tn.esprit.Entitys.User;
import tn.esprit.Entitys.stateRegion;

import javax.persistence.*;
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
    Date dateOfBirth;
    Date dateCreation;
    String email;
    String photo;
    Gender gender;
    stateRegion state;
    String city;
    int zip_code;
    String adresse;
    User user;
    Set<Appointment> appointments;
}



