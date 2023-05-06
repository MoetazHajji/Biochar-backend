package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.Gender;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.io.Serializable;

@Entity
@Builder
@Table(name ="Account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "created_at" )
    LocalDateTime createdAt ;
    @Column(name = "firstname" )
    String firstname;

    @Column(name = "lastname" )
    String lastname;

    @Column(name = "cin" )
    int cin;

    @Column(name = "phone" )
    int phone;

    @Column(name = "date_of_birth" )
    LocalDate dateOfBirth;

    @Column(name = "date_creation" )
    Date dateCreation;

    @Column(name = "hire_date" )
    LocalDate hireDate;

    @Column(name = "email" )
    String email;

    @Column(name = "gender" )
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "state" )
    @Enumerated(EnumType.STRING)
    StateRegion state;

    @Column(name = "city" )
    String city;

    @Column(name = "zip_code" )
    int zip_code;

    @Column(name = "adresse" )
    String adresse;

    @Column(name = "team" )
    @Enumerated(EnumType.STRING)
    Team team ;
    @Column(name = "shift" )
    @Enumerated(EnumType.STRING)
    Shift shift;

    @OneToOne(cascade = CascadeType.ALL)
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    Attachment attachment;

    @OneToMany(mappedBy = "account" , fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST,CascadeType.REMOVE} )
    Set<Appointment> appointments;


    // constractor without photo;
    public Account(String firstname, String lastname, int cin, int phone, LocalDate dateOfBirth,  String email, Gender gender,
                   StateRegion state,String city,int zip_code,String adresse) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cin = cin;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.state =state;
        this.city =city;
        this.zip_code =zip_code;
        this.adresse =adresse;
    }
}



