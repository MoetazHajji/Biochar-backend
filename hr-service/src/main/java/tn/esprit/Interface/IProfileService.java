package tn.esprit.Interface;

import tn.esprit.Entity.Profile;

import java.util.List;

public interface IProfileService {

    public Profile addProfile(Profile profile);
    public Profile updateProfile(Profile profile);
    public void deleteProfile(Integer idP);
    public List<Profile> retrieveAllProfiles();
    public Profile retrieveProfileById(Integer idP);

}
