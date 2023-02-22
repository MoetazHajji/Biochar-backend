package tn.esprit.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Min(0)
    float duration;

    @Min(0)
    @Max(23)
    float time;
    @NonNull
    @Column(unique = true)
    String title;

    String subject;
    String description;

    String image;

    @ManyToOne(cascade = CascadeType.REMOVE)
    Trainer trainer;

    @OneToMany(mappedBy = "training",fetch = FetchType.EAGER)
    Set<Trainee> trainees;

    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    Certificate certificate;

    @OneToMany(mappedBy = "training",cascade = CascadeType.REMOVE)
    Set<Review> reviews;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    Set<Quiz> quizes;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "training")
    Set<Demand> demands;
}
