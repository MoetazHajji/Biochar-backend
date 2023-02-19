package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.Type_product;

@Getter @Setter @ToString
@Builder @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name_product;
    String designation;
    String reference;
    Type_product type_product;
}
