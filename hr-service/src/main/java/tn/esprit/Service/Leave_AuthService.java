package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Interface.ILeave_AuthService;
import tn.esprit.Repository.Leave_AuthorizationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Leave_AuthService implements ILeave_AuthService{

    private final Leave_AuthorizationRepository leave_authorizationRepository;

    @Override
    public Leave_Authorization addLeaveAuth(Leave_Authorization la) {
        return leave_authorizationRepository.save(la);
    }

    @Override
    public Leave_Authorization updateLeaveAuth(Leave_Authorization la) {
        return leave_authorizationRepository.save(la);
    }

    @Override
    public void deleteLeaveAuth(Integer idLA) {
        leave_authorizationRepository.deleteById(idLA);
    }

    @Override
    public List<Leave_Authorization> retrieveAllLeaveAuths() {
        List<Leave_Authorization> leaveAuthorizations = new ArrayList<>();
        leave_authorizationRepository.findAll().forEach(leaveAuthorizations::add);
        return leaveAuthorizations;
    }

    @Override
    public Leave_Authorization retrieveLeaveAuthById(Integer idLA) {
        return leave_authorizationRepository.findById(idLA).orElse(null);
    }

}
