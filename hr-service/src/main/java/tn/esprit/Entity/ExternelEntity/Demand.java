package tn.esprit.Entity.ExternelEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.Profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Demand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Temporal(TemporalType.DATE)
    Date date_d;
    String description_r;

    @JsonIgnore
    @OneToOne(mappedBy = "demand")
    Profile profile;

}
