package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter @Setter @ToString
@Builder @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfferDto {
    Long id;
    Date start_date;
    Date end_date;
    String quantity;
    Double prix;

    Long id_Product;
    String name_product;

    Long id_supplier;
    String name_supplier;
}
