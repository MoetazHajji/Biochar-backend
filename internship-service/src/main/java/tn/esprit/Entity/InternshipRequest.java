package tn.esprit.Entity;

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
    int duration;
    String motif;
    String motivation;
    @NotNull
    String CV;
    @Enumerated(EnumType.STRING)
     Status status;
    Date start_date;
    Date end_date;
    Date sending_date;
    @ManyToOne
    Internship internship;







}
