package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.DemandDto;
import tn.esprit.Entity.Demand;
import tn.esprit.Interface.IDemandService;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Mapper.DemandMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("demand")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DemandController {
    final IDemandService demandService;

    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDto add_request(@RequestBody DemandDto d)
    {

        return DemandMapper
                .mapToDto(demandService
                        .add_demand(DemandMapper
                                .mapToEntity(d,trainingService)));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void delete_request(@PathVariable("id") Long id)
    {
        demandService.delete_demand(id);
    }

    @GetMapping
    public List<DemandDto> getAll_request()
    {
        List<DemandDto> demandDtos = new ArrayList<>();
        demandService.getAll_demands()
                .forEach(demand -> demandDtos.add(DemandMapper.mapToDto(demand)));
        return demandDtos;
    }

    @DeleteMapping("all")
    public void delete_all()
    {
        demandService.delete_all();
    }
}
