package tn.esprit.Mappers;

import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Appointment;

public class AppointmentMapper {
    public static Appointment mapToEntity(AppointmentDto appointmentDto){
        return Appointment.builder()
                .id(appointmentDto.getId())
                .reason(appointmentDto.getReason())
                .createdAt (appointmentDto.getCreatedAt())
                .comments(appointmentDto.getComments())
                .firstVisit (appointmentDto.isFirstVisit())
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
                .FirstVisit (appointment.isFirstVisit())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentStartTime(appointment.getAppointmentStartTime())
                .appointmentEndTime(appointment.getAppointmentEndTime())
                .appointmentStatus(appointment.getAppointmentStatus())
                .build();
    }
}