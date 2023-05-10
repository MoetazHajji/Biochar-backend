package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "reason" )
    String reason;

    @Column(name = "created_at" )
    LocalDateTime createdAt ;

    @Column(name = "comments" )
    String comments;

    @Column(name = "is_first_visit" )
    boolean firstVisit ;


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

    public Appointment(String reason, String comments, boolean firstVisit) {
        this.reason=reason;   this.comments=comments;  this.firstVisit=firstVisit;
    }

    public Appointment(String reason, String comments, boolean firstVisit, LocalDate appointmentDate, LocalTime appointmentStartTime, LocalTime appointmentEndTime) {
        this.reason = reason;
        this.comments = comments;
        this.firstVisit = firstVisit;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
    }
}
