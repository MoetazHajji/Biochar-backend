package tn.esprit.Dto;

import lombok.*;

import java.util.Date;
@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class TestResultDto {
    private int idTestResult;
    private String Teest;
    private String RÉSULTAT;
    private String UNITÉS;
    private String PLAGE_DE_RÉFÉRENCE;
    private Date date;
}
