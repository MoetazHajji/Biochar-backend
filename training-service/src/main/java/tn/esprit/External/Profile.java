package tn.esprit.External;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile implements Serializable {
    //@Id
    int id;
    String skills;
    String knowledge;
    String experience;
}
