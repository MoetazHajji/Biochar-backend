package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InternshipRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String duration;
    @NotNull
    String nom;
    @NotNull
    String prenom;
    String motif;
    String motivation;
    @NotNull
    String CV;
    @Enumerated(EnumType.STRING)
     Status status = Status.pending;
    @Temporal(TemporalType.DATE)
    Date start_date;
    @Temporal(TemporalType.DATE)
    Date end_date;

    @OneToOne(mappedBy = "internshipRequest")
            @JsonIgnore
    Internship internship;







}
