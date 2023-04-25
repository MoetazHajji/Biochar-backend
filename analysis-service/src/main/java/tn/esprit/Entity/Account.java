package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table( name = "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String Firstname;
    private String Lastname;
    private String Cin;
    private String Phone;
    private Date dateofbirdh;
    private String Email;
    private String Pasword;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="account")
    Set<Sample> samples;
    @OneToOne(mappedBy = "account")
    Medicalcard medicalcard;



}
