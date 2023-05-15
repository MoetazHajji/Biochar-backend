package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.ProfileDto;
import tn.esprit.Service.ProfileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Profile")
public class ProfileRestController {

    private final ProfileService profileService;

    @PostMapping("/addProfile")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileDto addProfile(@RequestBody ProfileDto profile){
        return profileService.addProfile(profile);
    }

    @PutMapping("/updateProfile")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDto updateProfile(@RequestBody ProfileDto profile){
        return profileService.updateProfile(profile);
    }

    @DeleteMapping("/deleteProfile/{idP}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable("idP") Long idP){
        profileService.deleteProfile(idP);
    }

    @GetMapping("/getAllProfiles")
    public List<ProfileDto> getAllProfiles(){
        return profileService.retrieveAllProfiles();
    }

    @GetMapping("/getProfileById/{idP}")
    public ProfileDto getProfileById(@PathVariable("idP") Long idP){
        return profileService.retrieveProfileById(idP);
    }

    @GetMapping("/getProfileByAcc/{idA}")
    public ProfileDto getProfileByAcc(@PathVariable("idA") Long idA){
        return profileService.retrieveProfileByAccount(idA);
    }

    @PostMapping("/updateExperience")
    @ResponseStatus(HttpStatus.OK)
    public void updateExperience(){
        profileService.updateExperience();
    }

    @PostMapping("addAndAssignProfileToAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileDto addAndAssignProfileToAccount(@RequestBody ProfileDto profile){
        return profileService.addAndAssignProfileToAccount(profile);
    }

}
