package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.CalenderGroupWSDto;
import tn.esprit.Dto.CalenderWSDto;
import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Service.Work_ScheduleService;

import java.time.LocalDate;
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
    public List<CalenderGroupWSDto> getAllWorkSchedules(){
        return work_scheduleService.retrieveAllWork_Schedule();
    }

    @GetMapping("/getWorkSchedulesByAcc/{idA}")
    public List<CalenderWSDto> getWorkSchedulesByAcc(@PathVariable("idA") Long idA){
        return work_scheduleService.retrieveWork_ScheduleByAccount(idA);
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

    @GetMapping("/getWorkSchedulesByOneDate/{date}")
    public List<Work_ScheduleDto> getWorkSchedulesByOneDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return work_scheduleService.retrieveWorkScheduleByDate(date);
    }

    @GetMapping("/getWorkScheduleByPeriod/{startDate}/{endDate}")
    public List<Work_ScheduleDto> getWorkScheduleByPeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return work_scheduleService.retrieveWorkScheduleByPeriod(startDate, endDate);
    }

    @PostMapping("/createDailyWorkSchedule/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public void createDailyWorkSchedule(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        work_scheduleService.createDailyWorkSchedule(startDate, endDate);
    }

    @DeleteMapping("/deleteWorkScheduleByDate/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkScheduleByPeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        work_scheduleService.deleteWorkScheduleByPeriod(startDate, endDate);
    }

    @PostMapping("/createWeekendWorkSchedule/{sunday}")
    @ResponseStatus(HttpStatus.OK)
    public void createWeekendWorkSchedule(@PathVariable("sunday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate sunday){
        work_scheduleService.createWeekendWorkSchedule(sunday);
    }

    @DeleteMapping("/deleteWorkScheduleByDate/{sunday}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkScheduleByDate(@PathVariable("sunday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate sunday){
        work_scheduleService.deleteWorkScheduleByDate(sunday);
    }

}
