package tn.esprit.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.AppointmentStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor //No Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDto {
    long id;
    String reason;
    LocalDateTime createdAt ;
    String comments;
    LocalDate appointmentDate;
    LocalTime appointmentStartTime;
    LocalTime appointmentEndTime;
    AppointmentStatus appointmentStatus;
    boolean FirstVisit ;

    public AppointmentDto(String reason, LocalDateTime createdAt, LocalDate appointmentDate, LocalTime appointmentStartTime) {
        this.reason = reason;
        this.createdAt = createdAt;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
    }
    public AppointmentDto(String reason) {
        this.reason = reason;
    }
}
