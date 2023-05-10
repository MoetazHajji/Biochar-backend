package tn.esprit.Services;

import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AppointmentDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Models.AuthenticationResponse;

public interface IAccountService extends IGenericCRUD<AccountDto>{
    AccountDto assignUserToAccount(Long idUser, Long idAccount);
    AccountDto  selectbyUsername(String  Usename);
    AuthenticationResponse addAppointementToUsername(String username , AppointmentDto object );
}
