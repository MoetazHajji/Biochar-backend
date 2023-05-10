package tn.esprit.Mappers;

import tn.esprit.Dto.AccountDtoTopic;
import tn.esprit.Entity.Account;

public class AccountMapper {


    public static Account mapToEntity(AccountDtoTopic accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .Firstname(accountDto.getFirstname())
                .Lastname(accountDto.getLastname())
                .Phone(accountDto.getPhone())
                .dateofbirdh(accountDto.getDateOfBirth())
                .Email(accountDto.getEmail())
                .gender(accountDto.getGender())
                .build();
    }
    public static AccountDtoTopic mapToDto(Account account){
        return AccountDtoTopic.builder()
                .id(account.getId())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .phone(account.getPhone())
                .dateOfBirth(account.getDateofbirdh())
                .email(account.getEmail())
                .gender(account.getGender())
                .build();
    }
}

