package tn.esprit.Dto;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class TestDto {
    private int idTest;
    private String nameTest;
    private float price;
    private LocalDate dateTest;
}
