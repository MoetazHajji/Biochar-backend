package tn.esprit.External;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile_e {
    Long id;
    String skills;
    String knowledge;
    int experience;
    String email;
}
