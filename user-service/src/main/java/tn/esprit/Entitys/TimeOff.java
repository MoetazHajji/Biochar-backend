package tn.esprit.Entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;



@Entity
@Builder
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


    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    public TimeOff(String titre, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        this.titre = titre;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }
}
