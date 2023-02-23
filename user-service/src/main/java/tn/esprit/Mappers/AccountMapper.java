package tn.esprit.Mappers;

import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Gender;
import tn.esprit.Entitys.User;
import tn.esprit.Entitys.stateRegion;

import java.util.Date;

public class AccountMapper {
    public static Account mapToEntity(AccountDto accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .firstname(accountDto.getFirstname())
                .lastname(accountDto.getLastname())
                .cin(accountDto.getCin())
                .phone(accountDto.getPhone())
                .dateOfBirth(accountDto.getDateOfBirth())
                .dateCreation(accountDto.getDateCreation())
                .email(accountDto.getEmail())
                .photo(accountDto.getPhoto())
                .gender(accountDto.getGender())
                .state(accountDto.getState())
                .city(accountDto.getCity())
                .zip_code(accountDto.getZip_code())
                .adresse(accountDto.getAdresse())
                .user(accountDto.getUser())
                .appointments(accountDto.getAppointments())
                .build();
    }
    public static AccountDto mapToDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .cin(account.getCin())
                .phone(account.getPhone())
                .dateOfBirth(account.getDateOfBirth())
                .dateCreation(account.getDateCreation())
                .email(account.getEmail())
                .photo(account.getPhoto())
                .gender(account.getGender())
                .state(account.getState())
                .city(account.getCity())
                .zip_code(account.getZip_code())
                .adresse(account.getAdresse())
                .user(account.getUser())
                .appointments(account.getAppointments())
                .build();
    }
}










