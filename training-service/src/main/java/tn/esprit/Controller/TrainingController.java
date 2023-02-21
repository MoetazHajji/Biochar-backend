package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainingService;

import javax.ws.rs.QueryParam;

import java.util.List;

@RestController
@RequestMapping("training")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrainingController {
    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Training add_request(@RequestBody Training t)
    {
        return trainingService.add_training(t);
    }

    @PostMapping("imaged")
    @ResponseStatus(HttpStatus.CREATED)
    public Training add_training(@RequestPart Training t,@RequestPart MultipartFile image)
    {
        return trainingService.add_training_with_image(t,image);
    }

    @DeleteMapping("{id}")
    public void delete_training(@PathVariable("id") Long id)
    {
        trainingService.delete_training(id);
    }

    @GetMapping
    public List<Training> getAll_training()
    {

        return trainingService.getAll_training();
    }

    @GetMapping("/{id}")
    public Training getById_trainer(@PathVariable("id") Long id)
    {
        return trainingService.getById_training(id);
    }

    @PutMapping("assignTrainees")
    public boolean add_Trainees_to_training(@QueryParam("id_training") Long id_trining,@QueryParam("id_trinee") List<Long> id_trainee)
    {
        return trainingService.add_Trainees_To_Training(id_trining,id_trainee);
    }
    @PutMapping("assignTrainer")
    public boolean add_Trainer_to_training(@QueryParam("id_training") Long id_trining,@QueryParam("id_trainer") Long id_trainer){
        return trainingService.add_Trainer_To_Training(id_trining,id_trainer);
        }
    @GetMapping("sorted")
    public List<Training> get_sorted_trainings(@QueryParam("by") int by) {
        return trainingService.get_sorted_trainings(by);
    }

    @DeleteMapping("all")
    public void delete_all()
    {
      trainingService.delete_all();
    }

}
