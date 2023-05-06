package tn.esprit.Services;

import tn.esprit.Dto.AccountDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.TimeOff;

public interface IAccountService extends IGenericCRUD<AccountDto>{
    AccountDto assignUserToAccount(Long idUser, Long idAccount);
    AccountDto  selectbyUsername(String  Usename);
}
