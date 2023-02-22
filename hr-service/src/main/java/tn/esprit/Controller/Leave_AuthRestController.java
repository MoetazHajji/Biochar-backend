package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Service.Leave_AuthService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("LeaveAuth")
public class Leave_AuthRestController {

    private final Leave_AuthService leave_authService;

    @PostMapping("/addLeaveAuth")
    @ResponseStatus(HttpStatus.CREATED)
    public Leave_AuthorizationDto addLA(@RequestBody Leave_AuthorizationDto leaveAuthorization){
        return leave_authService.addLeaveAuth(leaveAuthorization);
    }

    @PutMapping("/updateLeaveAuth")
    @ResponseStatus(HttpStatus.OK)
    public Leave_AuthorizationDto updateLA(@RequestBody Leave_AuthorizationDto leaveAuthorization){
        return leave_authService.updateLeaveAuth(leaveAuthorization);
    }

    @DeleteMapping("/deleteLeaveAuth/{idLA}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLA(@PathVariable("idLA") Long idLA){
        leave_authService.deleteLeaveAuth(idLA);
    }

    @GetMapping("/getAllLA")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Leave_AuthorizationDto> getAllLA(){
        return leave_authService.retrieveAllLeaveAuths();
    }

    @GetMapping("/getLAById/{idLA}")
    @ResponseStatus(HttpStatus.FOUND)
    public Leave_AuthorizationDto getLAById(@PathVariable("idLA") Long idLA){
        return leave_authService.retrieveLeaveAuthById(idLA);
    }

}
