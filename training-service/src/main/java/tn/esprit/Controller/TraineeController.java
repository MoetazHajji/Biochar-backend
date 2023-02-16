package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Trainee;
import tn.esprit.Interface.ITraineeService;

import java.util.List;

@RestController
@RequestMapping("trainee")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TraineeController {
    final ITraineeService traineeService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee add_trainee(@RequestBody Trainee r)
    {

        return traineeService.add_trainee(r);
    }

    @DeleteMapping("{id}")
    public void delete_trainee(@PathVariable("id") Long id)
    {
        traineeService.delete_trainee(id);
    }

    @GetMapping
    public List<Trainee> getAll_trainee()
    {

        return traineeService.getAll_trainee();
    }

    @GetMapping("/{id}")
    public Trainee getById_trainee(@PathVariable("id") Long id)
    {
        return traineeService.getById_trainee(id);
    }
}
