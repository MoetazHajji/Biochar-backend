package tn.esprit.Mapper;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import tn.esprit.Dto.CertificateDto;
import tn.esprit.Entity.Certificate;
import tn.esprit.Interface.ITrainingService;

import java.util.Random;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CertificateMapper {

    public static Certificate mapToEntity(CertificateDto certificateDto, ITrainingService trainingService){

        Certificate certificate = Certificate.builder()
                .id(certificateDto.getId_certificate())
                .title(certificateDto.getTitle())
                .type(certificateDto.getType())
                .description_c(certificateDto.getDescription())
                .training(trainingService.get_By_Title_training(certificateDto.getTraining()))
                .number(0l)
                .build();

        return certificate;
    }

    public static CertificateDto mapToDto(Certificate certificate){
        String title = "";
        if(certificate.getTraining() != null)
            title = certificate.getTraining().getTitle();

        CertificateDto certificateDto = CertificateDto.builder()
                .id_certificate(certificate.getId())
                .title(certificate.getTitle())
                .type(certificate.getType())
                .description(certificate.getDescription_c())
                .number(certificate.getNumber())
                .training(title)
                .build();
        return certificateDto;
    }
}
