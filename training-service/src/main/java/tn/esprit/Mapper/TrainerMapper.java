package tn.esprit.Mapper;


import tn.esprit.Dto.TrainerDto;
import tn.esprit.Entity.Trainer;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainingService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainerMapper {

    public static Trainer mapToEntity(TrainerDto trainerDto, ITrainingService trainingService){
        Set<Training> trainingSet = new HashSet<>();
        for(String title : trainerDto.getTrainings())
        {
            Training training = trainingService.get_By_Title_training(title);
            if(training != null)
                trainingSet.add(training);
        }

        Trainer trainer = Trainer.builder()
                .id(trainerDto.getId_trainer())
                .email(trainerDto.getEmail())
                .firstname(trainerDto.getFirstname())
                .lastname(trainerDto.getLastname())
                .phone_number(trainerDto.getPhone())
                .trainings(trainingSet)
                .build();
        return trainer;
    }


    public static TrainerDto mapToDto(Trainer trainer){
        List<String> titles = new ArrayList<>();
        if(trainer.getTrainings() != null && trainer.getTrainings().size() >0)
        trainer.getTrainings().forEach(training -> {titles.add(training.getTitle());});
        else
            titles.add("empty");
        TrainerDto trainerDto = TrainerDto.builder()
                .id_trainer(trainer.getId())
                .firstname(trainer.getFirstname())
                .lastname(trainer.getLastname())
                .email(trainer.getEmail())
                .phone(trainer.getPhone_number())
                .trainings(titles)
                .build();
        return trainerDto;
    }
}
