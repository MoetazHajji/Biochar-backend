package tn.esprit.Mappers;

import tn.esprit.Dto.TestDto;
import tn.esprit.Entity.Test;

public class TestMapper {
    public static TestDto mapToDto(Test test){

        TestDto testDto = TestDto.builder()
                .idTest(test.getIdTest())
                .nameTest(test.getNameTest())
                .price(test.getPrice())
                .dateTest(test.getDateTest())
                .build();
        return testDto;
    }
    public static Test mapToEntity(TestDto testDto ){
               Test test = Test.builder()
                .idTest(testDto.getIdTest())
                .nameTest(testDto.getNameTest())
                .price(testDto.getPrice())
                       .dateTest(testDto.getDateTest())
                .build();
        return test;
    }


}
