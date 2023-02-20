package tn.esprit.Entitys;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name ="Account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "firstname" )
    String firstname;

    @Column(name = "lastname" )
    String lastname;

    @Column(name = "cin" )
    int cin;

    @Column(name = "phone" )
    int phone;

    @Column(name = "date_of_birth" )
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Column(name = "date_creation" )
    @Temporal(TemporalType.DATE)
    Date dateCreation;

    @Column(name = "email" )
    String email;

    @Column(name = "photo" )
    String photo;

    @Column(name = "gender" )
    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    Set<Appointment> appointments;

    public Account(String firstname, String lastname, int cin, int phone, Date dateOfBirth, Date dateCreation, String email, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cin = cin;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.dateCreation = dateCreation;
        this.email = email;
        this.gender = gender;
    }
}
