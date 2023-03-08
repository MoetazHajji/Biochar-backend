package tn.esprit.Entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Internship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    State state;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Subject subject;
    @OneToOne
    InternshipRequest internshipRequest;
    @OneToOne(cascade = CascadeType.PERSIST)
    Detail detail;
    @OneToOne(cascade = CascadeType.PERSIST)
    FollowUpSheet followUpSheet;


}
