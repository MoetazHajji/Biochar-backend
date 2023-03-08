package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Work_ScheduleDto {

    Long id_WS;
    LocalDate dateWork;
    LocalTime start_time;
    LocalTime end_time;
    Shift shift;

}
