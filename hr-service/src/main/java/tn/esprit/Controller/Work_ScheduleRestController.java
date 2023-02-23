package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Service.Work_ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("WorkSchedule")
public class Work_ScheduleRestController {

    private final Work_ScheduleService work_scheduleService;

    @PostMapping("/addWorkSchedule")
    @ResponseStatus(HttpStatus.CREATED)
    public Work_ScheduleDto addWorkSchedule(@RequestBody Work_ScheduleDto workSchedule){
        return work_scheduleService.addWork_Schedule(workSchedule);
    }

    @PutMapping("/updateWorkSchedule")
    @ResponseStatus(HttpStatus.OK)
    public Work_ScheduleDto updateWorkSchedule(@RequestBody Work_ScheduleDto workSchedule){
        return work_scheduleService.updateWork_Schedule(workSchedule);
    }

    @DeleteMapping("/deleteWorkSchedule/{idWS}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkSchedule(@PathVariable("idWS") Long idWS){
        work_scheduleService.deleteWork_Schedule(idWS);
    }

    @GetMapping("/getAllWorkSchedule")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Work_ScheduleDto> getAllWorkSchedules(){
        return work_scheduleService.retrieveAllWork_Schedule();
    }

    @GetMapping("/getWorkScheduleById/{idWS}")
    @ResponseStatus(HttpStatus.FOUND)
    public Work_ScheduleDto getWorkScheduleById(@PathVariable("idWS") Long idWS){
        return work_scheduleService.retrieveWork_ScheduleById(idWS);
    }
    @PostMapping("/AddAndAssignWorkScheduleToAccount/{idA}")
    @ResponseStatus(HttpStatus.OK)
    public Work_ScheduleDto AddAndAssignWorkScheduleToAccount(@RequestBody Work_ScheduleDto workSchedule, @PathVariable("idA") Long idA){
        return work_scheduleService.AddAndAssignWorkScheduleToAccount(workSchedule,idA);
    }

    @PutMapping("/assignWsToAcc/{idWs}/{idA}")
    @ResponseStatus(HttpStatus.OK)
    public Work_ScheduleDto assignWsToAcc(@PathVariable("idWs") Long idWs, @PathVariable("idA") Long idA){
        return work_scheduleService.assignWsToAcc(idWs,idA);
    }

}
