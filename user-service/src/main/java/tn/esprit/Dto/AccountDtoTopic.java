package tn.esprit.Dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDtoTopic {
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
    public AccountDtoTopic (LocalDateTime createdAt, String firstname, String lastname, int cin, int phone, LocalDate dateOfBirth, LocalDate hireDate, String email, String photo, Gender gender, StateRegion state, String city, int zip_code, String adresse, Roles role, Team team, Shift shift) {
        this.createdAt = createdAt;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cin = cin;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.email = email;
        this.photo = photo;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.zip_code = zip_code;
        this.adresse = adresse;
        this.role = role;
        this.team = team;
        this.shift = shift;
    }
}

