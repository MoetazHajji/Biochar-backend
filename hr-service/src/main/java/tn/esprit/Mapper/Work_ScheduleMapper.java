package tn.esprit.Mapper;

import tn.esprit.Dto.CalenderGroupWSDto;
import tn.esprit.Dto.CalenderWSDto;
import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Entity.Shift;
import tn.esprit.Entity.Work_Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class Work_ScheduleMapper {

    public static Work_ScheduleDto mapWorkScheduleToDto(Work_Schedule work_schedule){
        Work_ScheduleDto wsd = Work_ScheduleDto.builder()
                .id_WS(work_schedule.getId_WS())
                .dateWork(work_schedule.getDateWork())
                .start_time(work_schedule.getStart_time())
                .end_time(work_schedule.getEnd_time())
                .shift(tn.esprit.Dto.Shift.valueOf(work_schedule.getShift().name()))
                .account_id(work_schedule.getAccount().getId())
                .build();
        return wsd;
    }

    public static CalenderWSDto mapCalenderWorkScheduleToDto(Work_Schedule work_schedule){
        CalenderWSDto cwsd = CalenderWSDto.builder()
                .Id(work_schedule.getId_WS())
                .StartTime(convertToDate(work_schedule.getDateWork(), work_schedule.getStart_time()))
                .EndTime(convertToDate(work_schedule.getDateWork(), work_schedule.getEnd_time()))
                .Subject(work_schedule.getAccount().getFirstname() + work_schedule.getAccount().getLastname())
                .build();
        return cwsd;
    }
    public static List<CalenderGroupWSDto> mapCalenderGroupWorkScheduleToDto(List<Work_Schedule>  workScheduleList){
        List<CalenderGroupWSDto> calendarGroupList = workScheduleList.stream()
                .collect(Collectors.groupingBy(ws -> LocalDateTime.of(ws.getDateWork(), ws.getStart_time())))
                .entrySet()
                .stream()
                .map(entry -> {
                    CalenderGroupWSDto calendarGroup = new CalenderGroupWSDto();
                    calendarGroup.setId(entry.getValue().get(0).getId_WS());
                    calendarGroup.setStartTime(entry.getKey());
                    calendarGroup.setEndTime(LocalDateTime.of(entry.getValue().get(0).getDateWork(), entry.getValue().get(0).getEnd_time()));
                    calendarGroup.setListFistNameLastNameRole(entry.getValue().stream().map(ws ->
                            ws.getAccount().getFirstname()
                                    +" " +
                         ws.getAccount().getLastname()
                                    + " " +
                                    ws.getAccount().getRole()
                    ).collect(Collectors.toList()));
                    return calendarGroup;
                })
                .collect(Collectors.toList());

       return calendarGroupList;
    }
    public static LocalDateTime convertToDate(LocalDate localDate, LocalTime localTime){
        return   LocalDateTime.of(localDate, localTime);
    }

    public static Work_Schedule mapWorkScheduleToEntity(Work_ScheduleDto work_scheduleDto){
        Work_Schedule ws = Work_Schedule.builder()
                .id_WS(work_scheduleDto.getId_WS())
                .dateWork(work_scheduleDto.getDateWork())
                .start_time(work_scheduleDto.getStart_time())
                .end_time(work_scheduleDto.getEnd_time())
                .shift(Shift.valueOf(work_scheduleDto.getShift().name()))
                .build();
        return ws;
    }

}
