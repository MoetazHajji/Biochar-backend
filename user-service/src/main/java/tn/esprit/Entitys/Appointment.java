package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Builder
@Table(name ="Appointment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "reason" )
    String reason;

    @Column(name = "created_at" )
    Date createdAt ;

    @Column(name = "comments" )
    String comments;

    @Column(name = "is_first_visit" )
    boolean is_first_visit ;


    @Column(name = "appointment_date", columnDefinition = "DATE")
    private LocalDate appointmentDate;

    @Column(name = "appointment_start_time", columnDefinition = "TIME")
    private LocalTime appointmentStartTime;


    @Column(name = "appointment_end_time", columnDefinition = "TIME")
    private LocalTime appointmentEndTime;

    @Column(name = "appointment_status" )
    @Enumerated(EnumType.STRING)
    AppointmentStatus appointmentStatus;


    @JsonIgnore
    @ManyToOne()
    Account account;

    public Appointment(String reason, String comments, boolean is_first_visit) {
        this.reason=reason;   this.comments=comments;  this.is_first_visit=is_first_visit;
    }

    public Appointment(String reason, String comments, boolean is_first_visit, LocalDate appointmentDate, LocalTime appointmentStartTime, LocalTime appointmentEndTime) {
        this.reason = reason;
        this.comments = comments;
        this.is_first_visit = is_first_visit;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
    }
}
