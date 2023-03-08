package tn.esprit.Mappers;

import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Dto.TestDto;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Entity.Test;

public class TestMapper {
    public static TestDto mapToDto(Test test){

        TestDto testDto = TestDto.builder()
                .idTest(test.getIdTest())
                .nameTest(test.getNameTest())
                .price(test.getPrice())
                .build();
        return testDto;
    }
    public static Test mapToEntity(TestDto testDto ){
               Test test = Test.builder()
                .idTest(testDto.getIdTest())
                .nameTest(testDto.getNameTest())
                .price(testDto.getPrice())
                .build();
        return test;
    }


}
