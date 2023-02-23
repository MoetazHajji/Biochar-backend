package tn.esprit.Services;

import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;

public interface IAppointementService extends IGenericCRUD<AppointmentDto>{
    AppointmentDto  assignAppointmentToAccount(Long idAppointment, Long idAccount);
}
