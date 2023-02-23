package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierDto {
    Long id;
    String name_supplier;
    Long phone;
    String email;

    Long idOffer;
    String adress_rue;
}
