package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@ToString
@Builder
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
    private int Phone;
    private LocalDate dateofbirdh;
    private String Email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="account")
    Set<Sample> samples;
    @OneToOne(mappedBy = "account")
    Medicalcard medicalcard;



}
