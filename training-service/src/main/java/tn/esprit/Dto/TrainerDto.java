package tn.esprit.Dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class TrainerDto {
    Long id_trainer;
    String firstname;
    String lastname;
    String email;
    int phone;
    List<String> trainings;
}
