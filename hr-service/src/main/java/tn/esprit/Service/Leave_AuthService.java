package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Interface.ILeave_AuthService;
import tn.esprit.Mapper.Leave_AuthorizationMapper;
import tn.esprit.Repository.Leave_AuthorizationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Leave_AuthService implements ILeave_AuthService{

    private final Leave_AuthorizationRepository leave_authorizationRepository;

    @Override
    public Leave_AuthorizationDto addLeaveAuth(Leave_AuthorizationDto la) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.save(Leave_AuthorizationMapper.mapLeaveAuthToEntity(la));
       // Leave_Authorization leaveAuthorization = Leave_AuthorizationMapper.mapLeaveAuthToEntity(la);

        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }

    @Override
    public Leave_AuthorizationDto updateLeaveAuth(Leave_AuthorizationDto la) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.save(Leave_AuthorizationMapper.mapLeaveAuthToEntity(la));
        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }

    @Override
    public void deleteLeaveAuth(Long idLA) {
        leave_authorizationRepository.deleteById(idLA);
    }

    @Override
    public List<Leave_AuthorizationDto> retrieveAllLeaveAuths() {
        List<Leave_Authorization> leaveAuthorizations = (List<Leave_Authorization>) leave_authorizationRepository.findAll();
        return leaveAuthorizations.stream().map(Leave_AuthorizationMapper::mapLeaveAuthToDto).collect(Collectors.toList());
    }

    @Override
    public Leave_AuthorizationDto retrieveLeaveAuthById(Long idLA) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.findById(idLA).orElse(null);
        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }

}
