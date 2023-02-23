package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @Column(name = "appointment_date" )
    @Temporal(TemporalType.DATE)
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date appointmentDate;

    @Column(name = "appointment_start_time" )
    @Temporal(TemporalType.TIME )
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE , pattern = "HH:mm:ss.SSSZ")
  // @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm:ss")
    Date appointmentStartTime;

    @Column(name = "appointment_end_time" )
    @Temporal(TemporalType.TIME)
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE , pattern = "HH:mm:ss.SSSZ")
  //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    Date appointmentEndTime;

    @Column(name = "appointment_status" )
    @Enumerated(EnumType.STRING)
    AppointmentStatus appointmentStatus;


    @JsonIgnore
    @ManyToOne()
    Account account;

    public Appointment(String reason, String comments, boolean is_first_visit) {
         this.reason=reason;   this.comments=comments;  this.is_first_visit=is_first_visit;
    }

    public Appointment(String reason, String comments, boolean is_first_visit, Date appointmentDate, Date appointmentStartTime, Date appointmentEndTime) {
        this.reason = reason;
        this.comments = comments;
        this.is_first_visit = is_first_visit;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
    }
}
