package tn.esprit.Dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ReviewDto {
    Long id_review;
    int rating;
    String review;
    String training_title;
    Date date;
    String user_name;
}
