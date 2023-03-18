package tn.esprit.Mappers;

import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.AppointmentStatus;
import tn.esprit.Entitys.User;

import java.util.Date;

public class AppointmentMapper {
    public static Appointment mapToEntity(AppointmentDto appointmentDto){
        return Appointment.builder()
                .id(appointmentDto.getId())
                .reason(appointmentDto.getReason())
                .createdAt (appointmentDto.getCreatedAt())
                .comments(appointmentDto.getComments())
                .is_first_visit (appointmentDto.is_first_visit())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .appointmentStartTime(appointmentDto.getAppointmentStartTime())
                .appointmentEndTime(appointmentDto.getAppointmentEndTime())
                .appointmentStatus(appointmentDto.getAppointmentStatus())
                .build();
    }
    public static AppointmentDto mapToDto(Appointment appointment){
        return AppointmentDto.builder()
                .id(appointment.getId())
                .reason(appointment.getReason())
                .createdAt (appointment.getCreatedAt())
                .comments(appointment.getComments())
                .is_first_visit (appointment.is_first_visit())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentStartTime(appointment.getAppointmentStartTime())
                .appointmentEndTime(appointment.getAppointmentEndTime())
                .appointmentStatus(appointment.getAppointmentStatus())
                .build();
    }
}

