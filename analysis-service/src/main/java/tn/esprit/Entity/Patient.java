package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table( name = "Patient")
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPatient")
    private int idPatient;
    private String Firstname;
    private String Lastname;
    private String Cin;
    private String Phone;
    private String Email;
    private String Pasword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="patient")
    Set<Sample> samples;




}
