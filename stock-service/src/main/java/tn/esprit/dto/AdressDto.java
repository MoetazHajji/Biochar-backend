package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter @ToString
@Builder @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdressDto {
    Long id;
    String Street;
    String city;
    String country;
    Long postal_code;

    String supplier_name;
    Long supplier_id;
}
