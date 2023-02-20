package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Profile;
import tn.esprit.Interface.IProfileService;
import tn.esprit.Repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Integer idP) {
        profileRepository.deleteById(idP);
    }

    @Override
    public List<Profile> retrieveAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(profiles::add);
        return profiles;
    }

    @Override
    public Profile retrieveProfileById(Integer idP) {
        return profileRepository.findById(idP).orElse(null);
    }
}
