package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "card_number obligatoir")
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}/\\d{2}/\\d{4}$", message = "Le format de la date doit être AAA-11/11/1111")
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
            @JsonIgnore
    Account account;
}
