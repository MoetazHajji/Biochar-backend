package tn.esprit.Mapper;

import tn.esprit.Dto.ProfileDto;
import tn.esprit.Entity.Profile;

public class ProfileMapper {

    public static ProfileDto mapProfileToDto(Profile profile){
        ProfileDto profileDto = ProfileDto.builder()
                .id(profile.getId())
                .skills(profile.getSkills())
                .knowledge(profile.getKnowledge())
                .experience(profile.getExperience())
                .build();
        return profileDto;
    }

    public static Profile mapProfileToEntity(ProfileDto profileDto){
        Profile profile = Profile.builder()
                .id(profileDto.getId())
                .skills(profileDto.getSkills())
                .knowledge(profileDto.getKnowledge())
                .experience(profileDto.getExperience())
                .build();
        return profile;
    }

}
