package tn.esprit.Mapper;

import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Entity.State_LA;
import tn.esprit.Entity.Type_LA;

public class Leave_AuthorizationMapper {

    public static Leave_AuthorizationDto mapLeaveAuthToDto(Leave_Authorization leave_authorization){
        Leave_AuthorizationDto lad = Leave_AuthorizationDto.builder()
                .id_LA(leave_authorization.getId_LA())
                .start_date(leave_authorization.getStart_date())
                .end_date(leave_authorization.getEnd_date())
                .remaining_days(leave_authorization.getRemaining_days())
                .cause(leave_authorization.getCause())
                .type_la(tn.esprit.Dto.Type_LA.valueOf(leave_authorization.getType_la().name()))
                .state_la(tn.esprit.Dto.State_LA.valueOf(leave_authorization.getState_la().name()))
                .build();
        return lad;
    }

    public static Leave_Authorization mapLeaveAuthToEntity(Leave_AuthorizationDto leave_authorizationDto){
        Leave_Authorization la = Leave_Authorization.builder()
                .id_LA(leave_authorizationDto.getId_LA())
                .start_date(leave_authorizationDto.getStart_date())
                .end_date(leave_authorizationDto.getEnd_date())
                .remaining_days(leave_authorizationDto.getRemaining_days())
                .cause(leave_authorizationDto.getCause())
                .type_la(Type_LA.valueOf(leave_authorizationDto.getType_la().name()))
                .state_la(State_LA.valueOf(leave_authorizationDto.getState_la().name()))
                .build();
        return null;
    }
}
