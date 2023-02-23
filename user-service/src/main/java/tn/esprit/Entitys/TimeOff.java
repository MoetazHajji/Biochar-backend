package tn.esprit.Entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name ="time_off")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "titre" )
    String titre;

    @Column(name = "type_time_Off" )
    @Enumerated(EnumType.STRING)
    TypeTimeOff typeTimeOff;

    @Column(name = "start_date" )
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,  timezone = "GMT+1" ,pattern = "yyyy-MM-dd")
    Date start_date;

    @Column(name = "start_time" )
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+1", pattern = "HH:mm:ss")
    Date start_time;

    @Column(name = "end_date" )
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC" , pattern = "yyyy-MM-dd")
    Date end_date;

    @Column(name = "end_time" )
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC",  pattern = "HH:mm:ss")
    Date end_time;

    public TimeOff(String titre, Date start_date, Date start_time, Date end_date, Date end_time) {
        this.titre = titre;
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_date = end_date;
        this.end_time = end_time;
    }
}
