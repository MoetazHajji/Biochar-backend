package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.ExternelEntity.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Field Work Date is required!")
    LocalDate dateWork;
    @NotNull(message = "Field Start Time is required!")
    LocalTime start_time;
    @NotNull(message = "Field End Time is required!")
    LocalTime end_time;
    @NotNull(message = "Field Shift is required!")
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
