package tn.esprit.exception.Mappers;

import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.*;

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
                .createdAt(accountDto.getCreatedAt())
                .email(accountDto.getEmail())
                .photo(accountDto.getPhoto())
                .gender(accountDto.getGender())
                .state(accountDto.getState())
                .city(accountDto.getCity())
                .zip_code(accountDto.getZip_code())
                .adresse(accountDto.getAdresse())
                .team(accountDto.getTeam())
                .shift(accountDto.getShift())
                //.user(accountDto.getUser())
                //.appointments(accountDto.getAppointments())
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
                .createdAt(account.getCreatedAt())
                .email(account.getEmail())
                .photo(account.getPhoto())
                .gender(account.getGender())
                .state(account.getState())
                .city(account.getCity())
                .zip_code(account.getZip_code())
                .adresse(account.getAdresse())
                //.user(account.getUser())
                //.appointments(account.getAppointments())
                .team(account.getTeam())
                .shift(account.getShift())
                .role(account.getUser().getRoles())
                .build();
    }
}










