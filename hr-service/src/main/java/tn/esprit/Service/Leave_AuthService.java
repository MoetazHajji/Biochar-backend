package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Account;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Entity.State_LA;
import tn.esprit.Entity.Type_LA;
import tn.esprit.Interface.ILeave_AuthService;
import tn.esprit.Mapper.Leave_AuthorizationMapper;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.Leave_AuthorizationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Leave_AuthService implements ILeave_AuthService{

     Leave_AuthorizationRepository leave_authorizationRepository;
  AccountRepository accountRepository;

    @Override
    public Leave_AuthorizationDto addLeaveAuth(Leave_Authorization la) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.save(la);
        //Leave_Authorization leaveAuthorization = Leave_AuthorizationMapper.mapLeaveAuthToEntity(la);

        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }

    @Override
    public Leave_AuthorizationDto updateLeaveAuth(Leave_Authorization la) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.save(la);
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

    @Override
    public Leave_AuthorizationDto addAndAssignLAToAccount(Leave_AuthorizationDto la) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.save(Leave_AuthorizationMapper.mapLeaveAuthToEntity(la));
        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }

    @Override
    public Leave_AuthorizationDto assignLAToAccount(Long idLA, Long idAccount) {
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.findById(idLA).orElse(null);
        Account account = accountRepository.findById(idAccount).orElse(null);
        leaveAuthorization.setAccount(account);
        leave_authorizationRepository.save(leaveAuthorization);
        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }


    @Override
    public Leave_AuthorizationDto countingRemainingdays(Long idAccount) {
        float rest;
        Leave_Authorization leaveAuthorization = leave_authorizationRepository.findByAccount_Id(idAccount);
        if (leaveAuthorization.getState_la().equals(State_LA.Accepted)){
            if (leaveAuthorization.getType_la().equals(Type_LA.Leave)){
                 rest = leaveAuthorization.getRemaining_days() - Math.abs(leaveAuthorization.getStart_date().getDay() - leaveAuthorization.getEnd_date().getDate());
            }else {
                rest = leaveAuthorization.getRemaining_days() - (leaveAuthorization.getAuthStartTime().getTime() - leaveAuthorization.getAuthEndTime().getTime());
            }
            leaveAuthorization.setRemaining_days(rest);
        }
        leave_authorizationRepository.save(leaveAuthorization);
        return Leave_AuthorizationMapper.mapLeaveAuthToDto(leaveAuthorization);
    }

}
