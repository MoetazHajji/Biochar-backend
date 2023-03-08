package tn.esprit.Interface;

import tn.esprit.Dto.Work_ScheduleDto;

import java.time.LocalDate;
import java.util.List;

public interface IWork_ScheduleService {

    public Work_ScheduleDto addWork_Schedule(Work_ScheduleDto workSchedule);
    public Work_ScheduleDto updateWork_Schedule(Work_ScheduleDto workSchedule);
    public void deleteWork_Schedule(Long idWS);
    public List<Work_ScheduleDto> retrieveAllWork_Schedule();
    public Work_ScheduleDto retrieveWork_ScheduleById(Long idWS);
    public Work_ScheduleDto AddAndAssignWorkScheduleToAccount(Work_ScheduleDto ws, Long idAccount);
    public Work_ScheduleDto assignWsToAcc(Long idWs, Long idA);
    public List<Work_ScheduleDto> retrieveWorkScheduleByDate(LocalDate date);
    public List<Work_ScheduleDto> retrieveWorkScheduleByPeriod(LocalDate startDate, LocalDate endDate);
    public void createDailyWorkSchedule(LocalDate startDate, LocalDate endDate);
    public void createWeekendWorkSchedule(LocalDate sunday);
    public void deleteWorkScheduleByDate(LocalDate date);
    public void deleteWorkScheduleByPeriod(LocalDate startDate, LocalDate endDate);

}
