package tn.esprit.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;




@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDetails {
     LocalDate datestamp;
     LocalTime timestamp;
     String message;
     String details;
}
