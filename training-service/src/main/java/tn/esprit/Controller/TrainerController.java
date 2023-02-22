package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.TrainerDto;
import tn.esprit.Entity.Trainer;
import tn.esprit.Interface.ITrainerService;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Mapper.TrainerMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("trainer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrainerController {
    final ITrainerService trainerService;

    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerDto add_trainer(@RequestBody TrainerDto r)
    {

      return TrainerMapper
              .mapToDto(trainerService
                      .add_trainer(TrainerMapper
                              .mapToEntity(r,trainingService)));
    }

    @DeleteMapping("{id}")
    public void delete_trainer(@PathVariable("id") Long id)
    {
        trainerService.delete_trainer(id);
    }

    @GetMapping
    public List<TrainerDto> getAll_trainer()
    {
        List<TrainerDto> trainerDtos = new ArrayList<>();
        trainerService.getAll_trainer()
                .forEach(trainer -> trainerDtos.add(TrainerMapper.mapToDto(trainer)));
        return trainerDtos;
    }

    @GetMapping("/{id}")
    public TrainerDto getById_trainer(@PathVariable("id") Long id)
    {

        return TrainerMapper.mapToDto(trainerService.getById_trainer(id));
    }

    @DeleteMapping("all")
    public void delete_all()
    {
        trainerService.delete_all();
    }
}
