package tn.esprit.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalenderWSDto {
    @JsonProperty("Id")
    Long Id;
    @JsonProperty("StartTime")
    LocalDateTime StartTime;
    @JsonProperty("EndTime")
    LocalDateTime EndTime;
    @JsonProperty("Subject")
    String Subject;
}
