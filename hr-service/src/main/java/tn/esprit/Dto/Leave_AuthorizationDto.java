package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leave_AuthorizationDto {

    Integer id_LA;
    Date start_date;
    Date end_date;
    Float remaining_days;
    String  cause;
    Type_LA type_la;
    State_LA state_la;

}
