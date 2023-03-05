package tn.esprit.Dto;


import lombok.*;
import tn.esprit.Entity.Type_Q;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class QuizDto {
    Long id_quiz;
    String question;
    String answer_1;
    String answer_2;
    String answer_3;
    String answer_4;
    int valid;
    Type_Q type;
}
