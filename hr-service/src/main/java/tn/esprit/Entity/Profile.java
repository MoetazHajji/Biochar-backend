package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entity.ExternelEntity.Account;
import tn.esprit.Entity.ExternelEntity.Demand;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String skills;
    String knowledge;
    @Max(value = 37, message = "Maximum years of experience must be 37! It's time for retirement!")
    Integer experience;

    @OneToOne(cascade = CascadeType.PERSIST)
    Account account;

    @JsonIgnore
    @OneToOne
    Demand demand;

}
