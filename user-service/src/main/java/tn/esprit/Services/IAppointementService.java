package tn.esprit.Services;

import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;

import java.time.LocalDate;

public interface IAppointementService extends IGenericCRUD<AppointmentDto>{
    AppointmentDto  assignAppointmentToAccount(Long idAppointment, Long idAccount);
    AppointmentDto  AddAppointmentAndAssignToAccount( Long idAccount , AppointmentDto appointmentDto );

     void sendMailSuggestionForUser(LocalDate StartDate ,  LocalDate LestDate , Appointment appt );
}
