package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Work_Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_WS;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date start_date;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date end_date;
    @NonNull
    @Enumerated(EnumType.STRING)
    Shift shift;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<Account> accounts;

}
