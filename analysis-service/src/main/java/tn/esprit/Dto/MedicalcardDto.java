package tn.esprit.Dto;

import lombok.*;
import tn.esprit.Entity.TypeDossier;


import java.util.Date;

@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class MedicalcardDto {
    private int idMedicalcard;

    //@Pattern(regexp = "[a-zA-Z0-9]{3}-\\d{4}-\\d{2}-\\d{2}", message = "Invalid format")
    //@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")

    private String card_number ;
    private Date date_creation;
    private String review;
    private String Doc;
    private TypeDossier typedossier;
}
