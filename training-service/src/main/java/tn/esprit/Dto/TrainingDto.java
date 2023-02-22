package tn.esprit.Dto;

import lombok.*;
import tn.esprit.Entity.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    float time;
    String title;
    String subject;
    String description;
    String image;

    String trainer;
    List<String> trainees;
    Long certificate;
    List<Long> reviews;
    List<String> quizes;
    List<Long> demands;
}
