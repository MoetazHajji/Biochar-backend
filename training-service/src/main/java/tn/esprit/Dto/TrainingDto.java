package tn.esprit.Dto;

import lombok.*;
import tn.esprit.Entity.Type_T;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class TrainingDto {
    Long id_training;
    Date start_date;
    Date end_date;
    String location;
    float duration;
    String time;
    String title;
    String subject;
    String description;
    String image;
    Type_T type;

    String trainer;
    List<String> trainees;
    Long certificate;
    List<Long> reviews;
    List<String> quizes;
    List<Long> demands;
}
