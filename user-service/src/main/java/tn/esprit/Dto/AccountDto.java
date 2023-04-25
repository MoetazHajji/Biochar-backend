package tn.esprit.Dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {
    long id;
    LocalDateTime createdAt;
    String firstname;
    String lastname;
    int cin;
    int phone;
    LocalDate dateOfBirth;
    LocalDate hireDate;
    String email;
    String photo;
    Gender gender;
    StateRegion state;
    String city;
    int zip_code;
    String adresse;
    Roles role;
    Team team ;
    Shift shift;
    //User user;
    //Set<Appointment> appointments;
}



