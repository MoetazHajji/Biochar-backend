package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileDto {

    Integer id;
    String skills;
    String knowledge;
    Integer experience;

}
