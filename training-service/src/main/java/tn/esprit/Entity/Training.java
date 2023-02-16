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
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Temporal (TemporalType.DATE)
    Date start_date;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    Date end_date;

    @NonNull
    String location;

    float duration;

    @NonNull
    @Column(unique = true)
    String title;

    String subject;

    String image;

    @ManyToOne(cascade = CascadeType.DETACH)
    Trainer trainer;

    @OneToMany(mappedBy = "training",fetch = FetchType.EAGER)
    Set<Trainee> trainees;

    @OneToOne(cascade = CascadeType.REMOVE)
    Certificate certificate;

    @OneToMany(mappedBy = "training")
    Set<Review> reviews;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    Set<Quiz> quizes;
}
