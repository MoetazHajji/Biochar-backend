package tn.esprit.Dto;


import lombok.*;



@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class TraineeDto {
    Long id_trainee;
    String firstname;
    String lastname;
    String email;
    String training_title;
}
