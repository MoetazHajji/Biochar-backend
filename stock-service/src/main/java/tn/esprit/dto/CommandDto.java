package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter @ToString
@Builder @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommandDto {
    Long id;
    Date date;
    String notice;
    Long quantity;

    Long idProduct;
    String name_product;
}
