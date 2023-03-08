package tn.esprit.Dto;

import lombok.*;

@ToString
@Builder
@Setter
@AllArgsConstructor
@Getter
public class TestDto {
    private int idTest;
    private String nameTest;
    private float price;
}
