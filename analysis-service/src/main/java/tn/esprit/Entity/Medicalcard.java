package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
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
    private int card_number ;
    private Date date_creation;
    private String review;
    private String Doc;
    @Enumerated(EnumType.STRING)
    private TypeDossier typedossier;
    public Medicalcard(String review,String Doc){
        this.review=review;
        this.Doc=Doc;
    }
    @OneToOne(mappedBy = "medicalcard")
    Account account;
}
