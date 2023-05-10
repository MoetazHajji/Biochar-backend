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
                .firstVisit (appointmentDto.isFirstVisit())
                .appointmentDate(
                        (appointmentDto.getAppointmentStart() == null ? null :appointmentDto.getAppointmentStart().toLocalDate())
                )
                .appointmentStartTime(
        (appointmentDto.getAppointmentStart() == null ? null : appointmentDto.getAppointmentStart().toLocalTime())
                )
                .appointmentEndTime(

        ( appointmentDto.getAppointmentEnd() == null ? null : appointmentDto.getAppointmentEnd().toLocalTime())
                )
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
                .appointmentStart(appointment.getAppointmentDate().atTime(appointment.getAppointmentStartTime()))
                .appointmentEnd(appointment.getAppointmentDate().atTime(appointment.getAppointmentEndTime()))
                .firstName((appointment.getAccount() == null ? null : appointment.getAccount().getFirstname() == null ? null :  appointment.getAccount().getFirstname()))
                .lastName((appointment.getAccount() == null ? null : appointment.getAccount().getLastname() == null ? null :  appointment.getAccount().getLastname()))
                .appointmentStatus(appointment.getAppointmentStatus())
                .build();
    }
}

