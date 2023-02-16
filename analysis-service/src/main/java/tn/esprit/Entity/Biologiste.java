package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "Biologiste")
public class Biologiste  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBiologiste")
    private int idBiologiste;
    private String Firstname;
    private String Lastname;
    private String Email;
    private String Pasword;
     @OneToOne(mappedBy = "biologiste")
    Medicalcard medicalcard;

}
