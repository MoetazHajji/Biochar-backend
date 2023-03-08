package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.ExternelEntity.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leave_Authorization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_LA;
    @Temporal(TemporalType.DATE)
    Date start_date;
    @Temporal(TemporalType.DATE)
    Date end_date;
    LocalTime authStartTime;
    LocalTime authEndTime;
    Long remaining_days;
    @NotBlank(message = "You have to add your reason for leave/authorization demand!")
    String cause;
    @Enumerated(EnumType.STRING)
    Type_LA type_la;
    @Enumerated(EnumType.STRING)
    State_LA state_la;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    Account account;

}
