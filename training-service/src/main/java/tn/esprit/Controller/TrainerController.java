package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Trainer;
import tn.esprit.Interface.ITrainerService;

import java.util.List;

@RestController
@RequestMapping("trainer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrainerController {
    final ITrainerService trainerService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Trainer add_trainer(@RequestBody Trainer r)
    {

        return trainerService.add_trainer(r);
    }

    @DeleteMapping("{id}")
    public void delete_trainer(@PathVariable("id") Long id)
    {
        trainerService.delete_trainer(id);
    }

    @GetMapping
    public List<Trainer> getAll_trainer()
    {

        return trainerService.getAll_trainer();
    }

    @GetMapping("/{id}")
    public Trainer getById_trainer(@PathVariable("id") Long id)
    {
        return trainerService.getById_trainer(id);
    }
}
