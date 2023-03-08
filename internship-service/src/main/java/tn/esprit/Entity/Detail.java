package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Detail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String university;
    String location_university;
    String phone_university;
    @Enumerated(EnumType.STRING)
    Diploma diploma;
    String level_of_study;
    String specialite;
    @OneToOne(mappedBy = "detail")
            @JsonIgnore
    Internship internship;





}
