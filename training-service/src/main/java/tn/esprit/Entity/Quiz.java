package tn.esprit.Entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
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
    int valid_answer;

    @Enumerated(EnumType.STRING)
    Type_Q type_q;
}
