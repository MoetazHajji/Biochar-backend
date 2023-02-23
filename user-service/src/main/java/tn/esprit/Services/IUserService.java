package tn.esprit.Services;

import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.User;

public interface IUserService extends IGenericCRUD<UserDto>{
    String sendMailCode_ForgotPassword(String email);
    boolean confirmationCode(String code, String password);

}
