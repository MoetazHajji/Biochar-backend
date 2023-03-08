package tn.esprit.Mapper;

import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Entity.Shift;
import tn.esprit.Entity.Work_Schedule;

public class Work_ScheduleMapper {

    public static Work_ScheduleDto mapWorkScheduleToDto(Work_Schedule work_schedule){
        Work_ScheduleDto wsd = Work_ScheduleDto.builder()
                .id_WS(work_schedule.getId_WS())
                .dateWork(work_schedule.getDateWork())
                .start_time(work_schedule.getStart_time())
                .end_time(work_schedule.getEnd_time())
                .shift(tn.esprit.Dto.Shift.valueOf(work_schedule.getShift().name()))
                .build();
        return wsd;
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
