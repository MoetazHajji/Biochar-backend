package tn.esprit.Dto;

import lombok.*;

import java.util.Date;
@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class SampleDto {
    private Integer idSample;
    private Date date;
    private String numSample;
}
