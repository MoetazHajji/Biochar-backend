package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Work_Schedule;
import tn.esprit.Service.Work_ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("WorkSchedule")
public class Work_ScheduleRestController {

    private final Work_ScheduleService work_scheduleService;

    @PostMapping("/addWorkSchedule")
    @ResponseStatus(HttpStatus.CREATED)
    public Work_Schedule addWorkSchedule(@RequestBody Work_Schedule workSchedule){
        return work_scheduleService.addWork_Schedule(workSchedule);
    }

    @PutMapping("/updateWorkSchedule")
    @ResponseStatus(HttpStatus.OK)
    public Work_Schedule updateWorkSchedule(@RequestBody Work_Schedule workSchedule){
        return work_scheduleService.updateWork_Schedule(workSchedule);
    }

    @DeleteMapping("/deleteWorkSchedule/{idWS}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkSchedule(@PathVariable("idWS") Integer idWS){
        work_scheduleService.deleteWork_Schedule(idWS);
    }

    @GetMapping("/getAllWorkSchedule")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Work_Schedule> getAllWorkSchedules(){
        return work_scheduleService.retrieveAllWork_Schedule();
    }

    @GetMapping("/getWorkScheduleById/{idWS}")
    @ResponseStatus(HttpStatus.FOUND)
    public Work_Schedule getWorkScheduleById(@PathVariable("idWS") Integer idWS){
        return work_scheduleService.retrieveWork_ScheduleById(idWS);
    }


}
