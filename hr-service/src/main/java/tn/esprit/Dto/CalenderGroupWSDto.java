package tn.esprit.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalenderGroupWSDto {
    @JsonProperty("Id")
    Long Id;
    @JsonProperty("StartTime")
    LocalDateTime StartTime;
    @JsonProperty("EndTime")
    LocalDateTime EndTime;
    @JsonProperty("Subject")
    List<String> listFistNameLastNameRole;
}
