package tn.esprit.Interface;

import tn.esprit.Entity.Leave_Authorization;

import java.util.List;

public interface ILeave_AuthService {

    public Leave_Authorization addLeaveAuth(Leave_Authorization la);
    public Leave_Authorization updateLeaveAuth(Leave_Authorization la);
    public void deleteLeaveAuth(Integer idLA);
    public List<Leave_Authorization> retrieveAllLeaveAuths();
    public Leave_Authorization retrieveLeaveAuthById(Integer idLA);
}
