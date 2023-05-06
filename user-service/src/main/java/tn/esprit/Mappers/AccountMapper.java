package tn.esprit.Mappers;

import tn.esprit.Dto.AccountDto;
import tn.esprit.Dto.AccountDtoTopic;
import tn.esprit.Dto.AttachementDto;
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
                .gender(accountDto.getGender())
                .state(accountDto.getState())
                .city(accountDto.getCity())
                .zip_code(accountDto.getZip_code())
                .adresse(accountDto.getAdresse())
                .team(accountDto.getTeam())
                .hireDate(accountDto.getHireDate())
                .shift(accountDto.getShift())
                .appointments(accountDto.getAppointments())
                .build();
    }
    public static AccountDto mapToDto(Account account){
        String  downloadURL = ( account.getAttachment() == null ? null  :
                AttachmentMapper.mapToDto( account.getAttachment()  ).getDownloadURL() );
        return AccountDto.builder()
                .id(account.getId())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .cin(account.getCin())
                .phone(account.getPhone())
                .dateOfBirth(account.getDateOfBirth())
                .createdAt(account.getCreatedAt())
                .email(account.getEmail())
                .photo(downloadURL)
                .gender(account.getGender())
                .state(account.getState())
                .city(account.getCity())
                .zip_code(account.getZip_code())
                .adresse(account.getAdresse())
                .appointments(account.getAppointments())
                .team(account.getTeam())
                .hireDate(account.getHireDate())
                .shift(account.getShift())
                .role(  (account.getUser()==null ? null : account.getUser().getRoles() )    )
                .build();
    }
    public static AccountDtoTopic mapToDtoTopic(Account account){
        String  downloadURL = ( account.getAttachment() == null ? null  :
                AttachmentMapper.mapToDto( account.getAttachment()  ).getDownloadURL() );
        return AccountDtoTopic.builder()
                .id(account.getId())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .cin(account.getCin())
                .phone(account.getPhone())
                .dateOfBirth(account.getDateOfBirth())
                .createdAt(account.getCreatedAt())
                .email(account.getEmail())
                .photo(downloadURL)
                .gender(account.getGender())
                .state(account.getState())
                .city(account.getCity())
                .zip_code(account.getZip_code())
                .adresse(account.getAdresse())
                .team(account.getTeam())
                .hireDate(account.getHireDate())
                .shift(account.getShift())
                .role( (account.getUser()==null ? null : account.getUser().getRoles() )  )
                .build();
    }
}
