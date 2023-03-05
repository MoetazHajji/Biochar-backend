package tn.esprit.Entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    @Column(unique = true)
    String question;

    @NonNull
    String answer_1;

    @NonNull
    String answer_2;

    String answer_3;

    String answer_4;
    @NonNull
    @Min(1)
    @Max(4)
    int valid_answer;

    @Enumerated(EnumType.STRING)
    Type_Q type_q;
}
