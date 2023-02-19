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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NonNull
    String firstname;
    @NonNull
    String lastname;
    @NonNull
    int cin;
    @NonNull
    int phone;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date dateCreation;
    @NonNull
    String email;
    String photo;
    @NonNull
    @Enumerated(EnumType.STRING)
    Gender gender;

    @JsonIgnore
    @OneToOne
    Profile profile;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    Set<Leave_Authorization> leave_authorizations;

    @JsonIgnore
    @ManyToOne
    Work_Schedule work_schedule;
}
