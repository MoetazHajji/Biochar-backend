package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowUpSheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String description;
    @ElementCollection
    Set<Integer> scores;

    @OneToOne(mappedBy = "followUpSheet")
    @JsonIgnore
    Internship internship;
    @OneToMany(mappedBy = "followUpSheet",cascade = CascadeType.PERSIST)
    Set<Test> tests;

}
