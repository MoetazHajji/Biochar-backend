package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Service.Leave_AuthService;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("LeaveAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class Leave_AuthRestController {

    private final Leave_AuthService leave_authService;

    @PutMapping("/updateLeaveAuth/{idA}")
    @ResponseStatus(HttpStatus.OK)
    public Leave_AuthorizationDto updateLA(@RequestBody Leave_AuthorizationDto leaveAuthorization, @PathVariable("idA") Long idA){
        return leave_authService.updateLeaveAuth(leaveAuthorization, idA);
    }

    @DeleteMapping("/deleteLeaveAuth/{idLA}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLA(@PathVariable("idLA") Long idLA){
        leave_authService.deleteLeaveAuth(idLA);
    }

    @GetMapping("/getAllLA")
    public List<Leave_AuthorizationDto> getAllLA(){
        return leave_authService.retrieveAllLeaveAuths();
    }

    @GetMapping("/getLAById/{idLA}")
    public Leave_AuthorizationDto getLAById(@PathVariable("idLA") Long idLA){
        return leave_authService.retrieveLeaveAuthById(idLA);
    }

    @PostMapping("/addAndAssignLAToAccount/{idA}")
    @ResponseStatus(HttpStatus.CREATED)
    public Leave_AuthorizationDto addAndAssignLAToAccount(@RequestBody Leave_Authorization leaveAuthorization, @PathVariable("idA") Long idA){
        return leave_authService.addAndAssignLAToAccount(leaveAuthorization, idA);
    }

    /*@PutMapping("/assignLAToAccount/{idLA}/{idA}")
    @ResponseStatus(HttpStatus.OK)
    public Leave_AuthorizationDto assignLAToAccount(@PathVariable("idLA") Long idLA, @PathVariable("idA") Long idA){
        return leave_authService.assignLAToAccount(idLA,idA);
    }*/

    @PostMapping("/updatingRemainingdays")
    @ResponseStatus(HttpStatus.OK)
    public void updatingRemainingdays(){
        leave_authService.countingRemainingdays();
    }

    @PostMapping("/CheckAndUpdateLeaveStatus")
    @ResponseStatus(HttpStatus.OK)
    public void checkAndUpdateLeaveStatus(){
        leave_authService.checkAndUpdateLeaveStatus();
    }

    @GetMapping("/retrieveLAByAccountId/{idA}")
    @ResponseStatus(HttpStatus.OK)
    public List<Leave_AuthorizationDto> retrieveLAByAccountId(@PathVariable("idA") Long idA){
        return leave_authService.retrieveLAByAccountId(idA);
    }

    @GetMapping("/retrieveLeaveAuthByPeriod/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Leave_AuthorizationDto> retrieveLeaveAuthByPeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return leave_authService.retrieveLeaveAuthByPeriod(startDate, endDate);
    }

}
