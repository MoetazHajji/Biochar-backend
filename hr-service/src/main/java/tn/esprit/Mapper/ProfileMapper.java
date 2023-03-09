package tn.esprit.Mapper;

import tn.esprit.Dto.ProfileDto;
import tn.esprit.Entity.ExternelEntity.Account;
import tn.esprit.Entity.Profile;
import tn.esprit.Repository.AccountRepository;

public class ProfileMapper {

    public static ProfileDto mapProfileToDto(Profile profile){
        ProfileDto profileDto = ProfileDto.builder()
                .id(profile.getId())
                .skills(profile.getSkills())
                .knowledge(profile.getKnowledge())
                .experience(profile.getExperience())
                .account_id(profile.getAccount().getId())
                .build();
        return profileDto;
    }

    public static Profile mapProfileToEntity(ProfileDto profileDto, AccountRepository accountRepository){
        Account account = accountRepository.findById(profileDto.getAccount_id()).orElse(null);
        Profile profile = Profile.builder()
                .id(profileDto.getId())
                .skills(profileDto.getSkills())
                .knowledge(profileDto.getKnowledge())
                .experience(profileDto.getExperience())
                .account(account)
                .build();
        return profile;
    }

}
