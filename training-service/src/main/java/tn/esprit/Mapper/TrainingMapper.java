package tn.esprit.Mapper;



import tn.esprit.Dto.TrainingDto;
import tn.esprit.Entity.Training;
import tn.esprit.Entity.Type_T;

import java.util.ArrayList;
import java.util.List;


public class TrainingMapper {
    public static Training mapToEntity(TrainingDto trainingDto){

        Training training = Training.builder()
                .id(trainingDto.getId_training())
                .startdate(trainingDto.getStart_date())
                .enddate(trainingDto.getEnd_date())
                .location(trainingDto.getLocation())
                .duration(trainingDto.getDuration())
                .time(trainingDto.getTime())
                .title(trainingDto.getTitle())
                .subject(trainingDto.getSubject())
                .description(trainingDto.getDescription())
                .type_t(trainingDto.getType())
                .build();
        return training;
    }

    public static TrainingDto mapToDto(Training training){
        if(training != null) {
            String trainer = "";
            Long certificate = (long) 0;
            List<String> trainees = new ArrayList<>();
            List<Long> reviews = new ArrayList<>();
            List<String> quizes = new ArrayList<>();
            List<Long> demands = new ArrayList<>();


            //Trainer
            if (training.getTrainer() != null)
                trainer = training.getTrainer().getEmail();
            //Certificate
            if (training.getCertificate() != null)
                certificate = training.getCertificate().getNumber();
            //Trainees
            if (training.getTrainees() != null)
                training.getTrainees().forEach(trainee -> trainees.add(trainee.getEmail()));
            else
                trainees.add("");
            //Review
            if (training.getReviews() != null)
                training.getReviews().forEach(review -> reviews.add(review.getId()));
            else
                reviews.add((long) 0);

            //quizes
            if (training.getQuizes() != null)
                training.getQuizes().forEach(quiz -> quizes.add(quiz.getQuestion()));
            else
                quizes.add("");
            //Demands
            if (training.getDemands() != null)
                training.getDemands().forEach(demand -> demands.add(demand.getId()));
            else
                demands.add((long) 0);


            TrainingDto trainingDto = TrainingDto.builder()
                    .id_training(training.getId())
                    .start_date(training.getStartdate())
                    .end_date(training.getEnddate())
                    .location(training.getLocation())
                    .duration(training.getDuration())
                    .time(training.getTime())
                    .title(training.getTitle())
                    .subject(training.getSubject())
                    .description(training.getDescription())
                    .type(training.getType_t())
                    .image(training.getImage())
                    .trainer(trainer)
                    .trainees(trainees)
                    .certificate(certificate)
                    .reviews(reviews)
                    .quizes(quizes)
                    .demands(demands)
                    .build();
            return trainingDto;
        }
        return null;
    }
}
