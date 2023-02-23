package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Dto.ProfileDto;
import tn.esprit.Entity.Account;
import tn.esprit.Entity.Profile;
import tn.esprit.Interface.IProfileService;
import tn.esprit.Mapper.ProfileMapper;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.ProfileRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;

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

    @Override
    public void checkExperience() {
        Date dateNow = new Date(System.currentTimeMillis());
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        for (Account account:accounts){
            if(account.getDateCreation().getMonth()== dateNow.getMonth()){
                profileRepository.upgradeExperience();
                log.info("Experience updated with 1 year");
            }
        }

    }


    // @Scheduled(cron = "0 0 0 31 12 *")
//   @Scheduled(cron = "*/15 * * * * *")
//    public void updateExperience(){
//    Date dateNow = new Date(System.currentTimeMillis());
//    List<Account> accounts = new ArrayList<>();
//        accountRepository.findAll().forEach(accounts::add);
//        for (Account account:accounts){
//
//            int months = Math.abs(dateNow.getMonth() - account.getDateCreation().getMonth());
//            int year =  dateNow.getYear() - account.getDateCreation().getYear();
//            int diff = year*12 + months;
//           diff = (int) (diff /=12);
//           System.out.println("la valeur de diff ="+diff);
//           Profile profile = account.getProfile();
//           profile.setExperience(diff);
//           profileRepository.save(profile);
//            log.info("Experience updated!!");
//        }
//    }
}
