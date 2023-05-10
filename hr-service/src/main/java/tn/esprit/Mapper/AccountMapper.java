package tn.esprit.Mapper;

import tn.esprit.Dto.AccountDtoTopic;
import tn.esprit.Dto.Shift;
import tn.esprit.Entity.ExternelEntity.Account;

public class AccountMapper {
    public static Account mapToEntity(AccountDtoTopic accountDto){
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
                .shift(tn.esprit.Entity.Shift.valueOf(accountDto.getShift().name()))
                .role(accountDto.getRole())
                .build();
    }
    public static AccountDtoTopic mapToDto(Account account){
        return AccountDtoTopic.builder()
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
                .team(account.getTeam())
                .shift(Shift.valueOf(account.getShift().name()))
                .role(account.getRole())
                .build();
    }
}