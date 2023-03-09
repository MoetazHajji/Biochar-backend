package tn.esprit.Dto;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CertificateDto {
    Long id_certificate;
    String title;
    String type;
    String description;
    Long number;
    String training;
}
