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
public class Work_ScheduleDto {

    Integer id_WS;
    Date start_date;
    Date end_date;
    Shift shift;

}
