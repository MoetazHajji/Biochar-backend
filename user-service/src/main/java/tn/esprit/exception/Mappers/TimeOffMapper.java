package tn.esprit.exception.Mappers;

import tn.esprit.Dto.TimeOffDto;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.User;

public class TimeOffMapper {
    public static TimeOff mapToEntity(TimeOffDto timeOffDto){
        return TimeOff.builder()
                .id(timeOffDto.getId())
                .titre(timeOffDto.getTitre())
                .typeTimeOff(timeOffDto.getTypeTimeOff())
                .startDate(timeOffDto.getStartDate())
                .startTime(timeOffDto.getStartTime())
                .endDate(timeOffDto.getEndDate())
                .endTime(timeOffDto.getEndTime())
                .build();
    }
    public static TimeOffDto mapToDto(TimeOff timeOff){
        return TimeOffDto.builder()
                .id(timeOff.getId())
                .titre(timeOff.getTitre())
                .typeTimeOff(timeOff.getTypeTimeOff())
                .startDate(timeOff.getStartDate())
                .startTime(timeOff.getStartTime())
                .endDate(timeOff.getEndDate())
                .endTime(timeOff.getEndTime())
                .build();
    }
}
