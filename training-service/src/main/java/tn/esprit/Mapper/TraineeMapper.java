package tn.esprit.Mapper;


import tn.esprit.Dto.TraineeDto;
import tn.esprit.Entity.Trainee;
import tn.esprit.Interface.ITrainingService;

public class TraineeMapper {
    public static Trainee mapToEntity(TraineeDto traineeDto, ITrainingService trainingService){

        Trainee trainee = Trainee.builder()
                .id(traineeDto.getId_trainee())
                .firstname(traineeDto.getFirstname())
                .lastname(traineeDto.getLastname())
                .email(traineeDto.getEmail())
                .training(trainingService.get_By_Title_training(traineeDto.getTraining_title()))
                .build();
        return trainee;
    }


    public static TraineeDto mapToDto(Trainee trainee){
        String title = "";
        if(trainee.getTraining() != null)
            title = trainee.getTraining().getTitle();

        TraineeDto traineeDto = TraineeDto.builder()
                .id_trainee(trainee.getId())
                .firstname(trainee.getFirstname())
                .lastname(trainee.getLastname())
                .email(trainee.getEmail())
                .training_title(title)
                .build();
        return traineeDto;
    }
}
