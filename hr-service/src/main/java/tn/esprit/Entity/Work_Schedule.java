package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.ExternelEntity.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Work_Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_WS;
    LocalDate dateWork;
    LocalTime start_time;
    LocalTime end_time;
    @Enumerated(EnumType.STRING)
    Shift shift;

    @JsonIgnore
    @ManyToOne()
    Account account;

    public Work_Schedule(LocalDate dateWork, LocalTime start_time, LocalTime end_time, Shift shift) {
        this.dateWork = dateWork;
        this.start_time = start_time;
        this.end_time = end_time;
        this.shift = shift;
    }

    public Work_Schedule(LocalDate dateWork, LocalTime start_time, LocalTime end_time, Shift shift, Account account) {
        this.dateWork = dateWork;
        this.start_time = start_time;
        this.end_time = end_time;
        this.shift = shift;
        this.account = account;
    }
}
