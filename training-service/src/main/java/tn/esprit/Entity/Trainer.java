package tn.esprit.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String firstname;
    @NonNull
    String lastname;
    @NonNull
    @Column(unique = true)
    String email;
    @Column(unique = true)
    int phone_number;

    @Enumerated(EnumType.STRING)
    Type_T type_t;

    @OneToMany(mappedBy = "trainer",fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    Set<Training> trainings;
}
