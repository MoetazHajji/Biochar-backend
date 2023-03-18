package tn.esprit.Dto;

import lombok.*;

import java.time.LocalDate;
@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class SampleDto {
    private Integer idSample;
    private LocalDate date;
    private String numSample;
}
