package tn.esprit.Mapper;

import org.springframework.stereotype.Component;
import tn.esprit.Dto.Leave_AuthorizationDto;
import tn.esprit.Entity.Leave_Authorization;
import tn.esprit.Entity.State_LA;
import tn.esprit.Entity.Type_LA;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Leave_AuthorizationMapper {

    public static Leave_AuthorizationDto mapLeaveAuthToDto(Leave_Authorization leave_authorization){

        Long days = TimeUnit.HOURS.toDays(leave_authorization.getRemaining_days());
        Long remainingHours = leave_authorization.getRemaining_days() - TimeUnit.DAYS.toHours(days);


        Leave_AuthorizationDto lad = Leave_AuthorizationDto.builder()
                .id_LA(leave_authorization.getId_LA())
                .start_date(leave_authorization.getStart_date())
                .end_date(leave_authorization.getEnd_date())
                .authStartTime(leave_authorization.getAuthStartTime())
                .authEndTime(leave_authorization.getAuthEndTime())
                .remainingLeaveDays("You have "+days+" days and "+remainingHours+" hours left in your leave balance!")
                .cause(leave_authorization.getCause())
                .type_la(tn.esprit.Dto.Type_LA.valueOf(leave_authorization.getType_la().name()))
                .state_la(tn.esprit.Dto.State_LA.valueOf(leave_authorization.getState_la().name()))
                .account_id(leave_authorization.getAccount().getId())
                .build();
        return lad;
    }

    public static Leave_Authorization mapLeaveAuthToEntity(Leave_AuthorizationDto leave_authorizationDto){

        String inputString = leave_authorizationDto.getRemainingLeaveDays();
        Long num = extractNumbersFromString(inputString);

        Leave_Authorization la = Leave_Authorization.builder()
                .id_LA(leave_authorizationDto.getId_LA())
                .start_date(leave_authorizationDto.getStart_date())
                .end_date(leave_authorizationDto.getEnd_date())
                .authStartTime(leave_authorizationDto.getAuthStartTime())
                .authEndTime(leave_authorizationDto.getAuthEndTime())
                .remaining_days(num)
                .cause(leave_authorizationDto.getCause())
                .type_la(Type_LA.valueOf(leave_authorizationDto.getType_la().name()))
                .state_la(State_LA.valueOf(leave_authorizationDto.getState_la().name()))
                .build();
        return la;
    }


    public static Long extractNumbersFromString(String inputString) {
        Pattern pattern = Pattern.compile("\\d+");

        // Create a matcher object to search for the pattern in the input string
        Matcher matcher = pattern.matcher(inputString);

        List<Long> numbers = new ArrayList<>();

        // Loop through all matches found in the input string
        while (matcher.find()) {
            String numberString = matcher.group();
            Long number = Long.parseLong(numberString);
            numbers.add(number);
        }
        for (int i =0; i<numbers.size(); i++){
            Long days = numbers.get(0)*24;
            Long hours = numbers.get(1);
            return days+hours;
        }
        return null;
    }
}
