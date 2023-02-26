package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
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
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;
    @Temporal(TemporalType.DATE)
    Date dateCreation;
    @Temporal(TemporalType.DATE)
    Date hireDate;
    String email;
    String photo;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Enumerated(EnumType.STRING)
    stateRegion state;
    String city;
    int zip_code;
    String adresse;

    @JsonIgnore
    @OneToOne
    Profile profile;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    Set<Leave_Authorization> leave_authorizations;

    @JsonIgnore
    @ManyToMany (cascade = CascadeType.PERSIST, mappedBy = "accounts")
    Set<Work_Schedule> workSchedules;
}
