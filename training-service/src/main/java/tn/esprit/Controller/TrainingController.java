package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dto.TrainingDto;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Mapper.TrainingMapper;

import javax.ws.rs.QueryParam;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("training")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrainingController {
    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto add_request(@RequestBody TrainingDto t)
    {
        return TrainingMapper
                .mapToDto(trainingService
                        .add_training(TrainingMapper
                                .mapToEntity(t)));
    }

    @PostMapping("imaged")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto add_training(@RequestPart TrainingDto t,@RequestPart MultipartFile image)
    {
        return TrainingMapper
                .mapToDto(trainingService
                        .add_training_with_image(TrainingMapper
                                .mapToEntity(t),image));
    }

    @DeleteMapping("{id}")
    public void delete_training(@PathVariable("id") Long id)
    {
        trainingService.delete_training(id);
    }

    @GetMapping
    public List<TrainingDto> getAll_training()
    {
        List<TrainingDto> trainingDtos = new ArrayList<>();
        trainingService.getAll_training()
                .forEach(training -> trainingDtos.add(TrainingMapper.mapToDto(training)));
        return trainingDtos;
    }

    @GetMapping("/{id}")
    public TrainingDto getById_trainer(@PathVariable("id") Long id)
    {
        return TrainingMapper.mapToDto(trainingService.getById_training(id));
    }

    @GetMapping("/byTitle/{title}")
    public TrainingDto getBy_Title(@PathVariable("title") String title)
    {
        Training training = trainingService.get_By_Title_training(title);
        if(training !=null)
            return TrainingMapper.mapToDto(training);
        return null;
    }

    @PutMapping("assignTrainees")
    public boolean add_Trainees_to_training(@QueryParam("id_training") Long id_training,@QueryParam("id_trainee") List<Long> id_trainee)
    {
        return trainingService.add_Trainees_To_Training(id_training,id_trainee);
    }
    @PutMapping("assignTrainer")
    public boolean add_Trainer_to_training(@QueryParam("id_training") Long id_training,@QueryParam("id_trainer") Long id_trainer){
        return trainingService.add_Trainer_To_Training(id_training,id_trainer);
    }
    @GetMapping("sorted")
    public List<TrainingDto> get_sorted_trainings(@QueryParam("by") int by) {
        List<TrainingDto> trainingDtos = new ArrayList<>();
        trainingService.get_sorted_trainings(by).forEach(training -> trainingDtos.add(TrainingMapper.mapToDto(training)));
        return trainingDtos;
    }

    @DeleteMapping("all")
    public void delete_all()
    {
        trainingService.delete_all();
    }

    @PutMapping("add_quizes/{training_title}/{ids}")
    public TrainingDto affect_quizes_to_training(@PathVariable("ids")List<Long> ids,@PathVariable("training_title") String training_title)
    {
        return TrainingMapper.mapToDto(trainingService.affect_quizes_to_training(ids, training_title));
    }
}
