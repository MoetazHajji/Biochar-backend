package tn.esprit.Interface;

import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Leave_Authorization;

import java.util.Date;
import java.util.List;

public interface ILeave_AuthService {

   //s public Leave_AuthorizationDto updateLeaveAuth(Leave_Authorization la, Long idAs);
    public Leave_AuthorizationDto updateLeaveAuth(Leave_AuthorizationDto la, Long idA);
    public void deleteLeaveAuth(Long idLA);
    public List<Leave_AuthorizationDto> retrieveAllLeaveAuths();
    public Leave_AuthorizationDto retrieveLeaveAuthById(Long idLA);
    public Leave_AuthorizationDto addAndAssignLAToAccount(Leave_Authorization la, Long idA);
    public Leave_AuthorizationDto assignLAToAccount(Long idLA, Long idAccount);
    public void countingRemainingdays();
    public void checkAndUpdateLeaveStatus();
    public List<Leave_AuthorizationDto> retrieveLAByAccountId(Long idA);
    public List<Leave_AuthorizationDto> retrieveLeaveAuthByPeriod(Date startDate, Date endDate);

    //public List<Float> extractNumbersFromString(String inputString);

}
