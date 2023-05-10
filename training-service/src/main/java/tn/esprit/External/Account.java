package tn.esprit.External;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    long id;
    String email;


}



