package tn.esprit.Interface;

import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Leave_Authorization;

import java.util.List;

public interface ILeave_AuthService {

    public Leave_AuthorizationDto addLeaveAuth(Leave_Authorization la);
   // public Leave_AuthorizationDto addLeaveAuth(Leave_AuthorizationDto la);
    public Leave_AuthorizationDto updateLeaveAuth(Leave_Authorization la);
   // public Leave_AuthorizationDto updateLeaveAuth(Leave_AuthorizationDto la);
    public void deleteLeaveAuth(Long idLA);
    public List<Leave_AuthorizationDto> retrieveAllLeaveAuths();
    public Leave_AuthorizationDto retrieveLeaveAuthById(Long idLA);
    public Leave_AuthorizationDto addAndAssignLAToAccount(Leave_AuthorizationDto la);
    public Leave_AuthorizationDto assignLAToAccount(Long idLA, Long idAccount);
    public Leave_AuthorizationDto countingRemainingdays(Long idAccount);
}
