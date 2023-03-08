package tn.esprit.Dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class DemandDto {
    Long id_demand;
    String description;
    String training;
    Date date;
}
