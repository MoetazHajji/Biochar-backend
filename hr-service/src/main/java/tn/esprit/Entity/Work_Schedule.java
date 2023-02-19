package tn.esprit.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Work_Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_WS;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date start_date;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date end_date;
    @NonNull
    @Enumerated(EnumType.STRING)
    Shift shift;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "work_schedule")
    Set<Account> accounts;

}
