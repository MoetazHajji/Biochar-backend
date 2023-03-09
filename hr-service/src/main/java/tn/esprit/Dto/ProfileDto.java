package tn.esprit.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileDto {

    Long id;
    String skills;
    String knowledge;
    Integer experience;
    Long account_id;

}
