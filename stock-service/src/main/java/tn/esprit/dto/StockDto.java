package tn.esprit.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.State;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockDto {
    Long id;
    String unit;
    Double storage;
    Double free_storage;
    Double used_storage;
    Date date;
    String location;
    State state;

    List<String> product_names;
}
