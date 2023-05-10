package tn.esprit.Interface;

import tn.esprit.Dto.ProfileDto;

import java.util.List;

public interface IProfileService {

    public ProfileDto addProfile(ProfileDto profile);
    ProfileDto updateProfile(ProfileDto profile);
    public void deleteProfile(Long idP);
    public List<ProfileDto> retrieveAllProfiles();
    public ProfileDto retrieveProfileById(Long idP);
    public ProfileDto retrieveProfileByAccount(Long idA);
    public void updateExperience();
    public ProfileDto addAndAssignProfileToAccount(ProfileDto profile);


}
