package tn.esprit.Mappers;


import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Dto.SampleDto;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Entity.Sample;

public class SampleMapper {
    public static SampleDto mapToDto(Sample sample){

        SampleDto sampleDto = SampleDto.builder()
                .idSample(sample.getIdSample())
                .date(sample.getDate())
                .numSample(sample.getNumSample())
                .build();
        return sampleDto;
    }
    public static Sample mapToEntity(SampleDto sampleDto ){
        Sample sample = Sample.builder()
                .idSample(sampleDto.getIdSample())
                .date(sampleDto.getDate())
                .numSample(sampleDto.getNumSample())
                .build();
        return sample;
    }


}
