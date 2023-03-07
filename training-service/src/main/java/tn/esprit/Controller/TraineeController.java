package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.TraineeDto;
import tn.esprit.Dto.TrainingDto;
import tn.esprit.Entity.Trainee;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITraineeService;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Mapper.TraineeMapper;
import tn.esprit.Mapper.TrainingMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("trainee")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TraineeController {
    final ITraineeService traineeService;

    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto add_trainee(@RequestBody TraineeDto r)
    {

        return TraineeMapper
                .mapToDto(traineeService
                        .add_trainee(TraineeMapper
                                .mapToEntity(r,trainingService)));
    }

    @DeleteMapping("{id}")
    public void delete_trainee(@PathVariable("id") Long id)
    {
        traineeService.delete_trainee(id);
    }

    @GetMapping
    public List<TraineeDto> getAll_trainee()
    {
        List<TraineeDto> traineeDtos = new ArrayList<>();
        traineeService.getAll_trainee()
                .forEach(trainee -> traineeDtos.add(TraineeMapper.mapToDto(trainee)));
        return traineeDtos;
    }

    @GetMapping("/{id}")
    public TraineeDto getById_trainee(@PathVariable("id") Long id)
    {
        return TraineeMapper.mapToDto(traineeService.getById_trainee(id));
    }

    @DeleteMapping("all")
    public void delete_all()
    {
        traineeService.delete_all();
    }

    @PutMapping("submit/{id_trainee}")
    public int submit_Answer(@RequestBody Map<String,List<Integer>> answers, @PathVariable("id_trainee") Long id_trainee)
    {
        return traineeService.submit_Answer(answers, id_trainee);
    }

    @GetMapping("get_suits/{id_profile}")
    public List<TrainingDto> get_suits(@PathVariable("id_profile") int id_profile)
    {
        List<TrainingDto> trainings = new ArrayList<>();
        traineeService.get_suits(id_profile).forEach(training -> trainings.add(TrainingMapper.mapToDto(training)));
        return trainings;
    }

    @GetMapping("get_score/{id_profile}")
    public double get_score(@PathVariable("id_profile") int id_profile)
    {
       return traineeService.getScore(id_profile);
    }
}
