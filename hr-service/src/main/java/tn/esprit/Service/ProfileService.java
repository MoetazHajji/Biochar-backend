package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.ProfileDto;
import tn.esprit.Entity.Profile;
import tn.esprit.Interface.IProfileService;
import tn.esprit.Mapper.ProfileMapper;
import tn.esprit.Repository.ProfileRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileDto addProfile(ProfileDto profile) {
        Profile p = profileRepository.save(ProfileMapper.mapProfileToEntity(profile));
        return ProfileMapper.mapProfileToDto(p);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profile) {
        Profile p = profileRepository.save(ProfileMapper.mapProfileToEntity(profile));
        return ProfileMapper.mapProfileToDto(p);
    }

    @Override
    public void deleteProfile(Long idP) {
        profileRepository.deleteById(idP);
    }

    @Override
    public List<ProfileDto> retrieveAllProfiles() {
        List<Profile> profiles = (List<Profile>) profileRepository.findAll();
        return profiles.stream().map(ProfileMapper::mapProfileToDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto retrieveProfileById(Long idP) {
        Profile p = profileRepository.findById(idP).orElse(null);
        return ProfileMapper.mapProfileToDto(p);
    }
}
