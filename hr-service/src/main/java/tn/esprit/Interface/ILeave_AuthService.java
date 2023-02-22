package tn.esprit.Interface;

import tn.esprit.Dto.Leave_AuthorizationDto;

import java.util.List;

public interface ILeave_AuthService {

    public Leave_AuthorizationDto addLeaveAuth(Leave_AuthorizationDto la);
    public Leave_AuthorizationDto updateLeaveAuth(Leave_AuthorizationDto la);
    public void deleteLeaveAuth(Long idLA);
    public List<Leave_AuthorizationDto> retrieveAllLeaveAuths();
    public Leave_AuthorizationDto retrieveLeaveAuthById(Long idLA);
}
