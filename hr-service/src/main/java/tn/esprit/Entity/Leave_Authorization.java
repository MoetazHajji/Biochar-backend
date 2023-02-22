package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leave_Authorization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_LA;
   // @NonNull
    @Temporal(TemporalType.DATE)
    Date start_date;
   // @NonNull
    @Temporal(TemporalType.DATE)
    Date end_date;
    Float remaining_days;
    //@NonNull
    String  cause;
   // @NonNull
    @Enumerated(EnumType.STRING)
    Type_LA type_la;
   // @NonNull
    @Enumerated(EnumType.STRING)
    State_LA state_la;

    @JsonIgnore
    @ManyToOne
    Account account;

}
