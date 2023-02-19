package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter @Setter @ToString
@Builder @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfferCallDto {
    Long id;
    Date date_offer;
    String quantity;
    float prix;
}
