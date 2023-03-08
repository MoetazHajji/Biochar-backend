package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@ToString
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table( name = "Medicalcard")
public class Medicalcard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMedicalcard")
    private int idMedicalcard;
    //@NotBlank(message = "card_number obligatoir")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String card_number ;
    private  Date date_creation;
    //@NotBlank(message = "review obligatoir")
    private String review;
    //@NotBlank(message = "Doc obligatoir")
    private String Doc;
    @Enumerated(EnumType.STRING)
    private TypeDossier typedossier;
    public Medicalcard(String review,String Doc){
        this.review=review;
        this.Doc=Doc;
    }
    @OneToOne
    Account account;
}
