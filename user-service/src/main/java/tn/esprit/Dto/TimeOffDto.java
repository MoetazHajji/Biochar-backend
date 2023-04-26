package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.TypeTimeOff;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeOffDto {
    long id;
    String titre;
    TypeTimeOff typeTimeOff;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
}
