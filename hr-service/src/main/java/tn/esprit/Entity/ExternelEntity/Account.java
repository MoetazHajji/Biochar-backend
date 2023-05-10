package tn.esprit.Entity.ExternelEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String firstname;
    String lastname;
    int cin;
    int phone;
    LocalDate dateOfBirth;
    LocalDateTime createdAt;
    LocalDate hireDate;
    String email;
    String photo;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Enumerated(EnumType.STRING)
    StateRegion state;
    String city;
    int zip_code;
    String adresse;
    @Enumerated(EnumType.STRING)
    Roles role;
    @Enumerated(EnumType.STRING)
    Team team ;
    @Enumerated(EnumType.STRING)
    Shift shift;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    Profile profile;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    Set<Leave_Authorization> leave_authorizations;

    @JsonIgnore
    @OneToMany (mappedBy = "account")
    Set<Work_Schedule> workSchedules;


    public Account(String firstname, int cin, Roles role, Shift shift) {
        this.firstname = firstname;
        this.cin = cin;
        this.role = role;
        this.shift = shift;
    }

    public Account(String firstname, int cin, Roles role, Team team, Shift shift) {
        this.firstname = firstname;
        this.cin = cin;
        this.role = role;
        this.team = team;
        this.shift = shift;
    }
}