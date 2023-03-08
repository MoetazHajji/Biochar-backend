package tn.esprit.Dto;

import lombok.*;

@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class PatientDto {
    private int idPatient;
    private String Firstname;
    private String Lastname;
    private String Cin;
    private String Phone;
    private String Email;
    private String Pasword;
}
