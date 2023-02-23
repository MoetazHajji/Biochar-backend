package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leave_AuthorizationDto {

    Long id_LA;
    Date start_date;
    Date end_date;
    Date authStartTime;
    Date authEndTime;
    Float remaining_days;
    String  cause;
    Type_LA type_la;
    State_LA state_la;

}
