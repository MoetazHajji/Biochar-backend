package tn.esprit.Dto;


import lombok.*;
import tn.esprit.Entity.Type_Q;

import java.util.List;

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
    List<Integer> valid;
    Type_Q type;
}
