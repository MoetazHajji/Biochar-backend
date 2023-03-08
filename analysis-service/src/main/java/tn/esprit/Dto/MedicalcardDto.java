package tn.esprit.Dto;

import lombok.*;
import tn.esprit.Entity.TypeDossier;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class MedicalcardDto {
    private int idMedicalcard;
    private int card_number ;
    private Date date_creation;
    private String review;
    private String Doc;
    private TypeDossier typedossier;
}
