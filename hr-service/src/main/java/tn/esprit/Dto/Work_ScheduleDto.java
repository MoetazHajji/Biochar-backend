package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Work_ScheduleDto {

    Long id_WS;
    Date start_date;
    Date end_date;
    Shift shift;

}
