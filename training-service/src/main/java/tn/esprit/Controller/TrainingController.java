package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainingService;

import java.util.List;

@RestController
@RequestMapping("training")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrainingController {
    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Training add_trainer(@RequestBody Training r)
    {

        return trainingService.add_training(r);
    }

    @DeleteMapping("{id}")
    public void delete_trainer(@PathVariable("id") Long id)
    {
        trainingService.delete_training(id);
    }

    @GetMapping
    public List<Training> getAll_trainer()
    {

        return trainingService.getAll_training();
    }

    @GetMapping("/{id}")
    public Training getById_trainer(@PathVariable("id") Long id)
    {
        return trainingService.getById_training(id);
    }
}
