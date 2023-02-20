package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Service.Leave_AuthService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("LeaveAuth")
public class Leave_AuthRestController {

    private final Leave_AuthService leave_authService;

    @PostMapping("/addLeaveAuth")
    @ResponseStatus(HttpStatus.CREATED)
    public Leave_Authorization addLA(@RequestBody Leave_Authorization leaveAuthorization){
        return leave_authService.addLeaveAuth(leaveAuthorization);
    }

    @PutMapping("/updateLeaveAuth")
    @ResponseStatus(HttpStatus.OK)
    public Leave_Authorization updateLA(@RequestBody Leave_Authorization leaveAuthorization){
        return leave_authService.updateLeaveAuth(leaveAuthorization);
    }

    @DeleteMapping("/deleteLeaveAuth/{idLA}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLA(@PathVariable("idLA") Integer idLA){
        leave_authService.deleteLeaveAuth(idLA);
    }

    @GetMapping("/getAllLA")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Leave_Authorization> getAllLA(){
        return leave_authService.retrieveAllLeaveAuths();
    }

    @GetMapping("/getLAById/{idLA}")
    @ResponseStatus(HttpStatus.FOUND)
    public Leave_Authorization getLAById(@PathVariable("idLA") Integer idLA){
        return leave_authService.retrieveLeaveAuthById(idLA);
    }

}
