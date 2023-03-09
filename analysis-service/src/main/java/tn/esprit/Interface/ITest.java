package tn.esprit.Interface;

import tn.esprit.Dto.TestDto;
import tn.esprit.Entity.Test;

import java.util.List;

public interface ITest {


    TestDto addOrUpdateTest(TestDto testDto);

    void removeSTest(int idTest);

    Test retriveTest(int idTest);


    List<TestDto> retrieveAllTest();

    Test asigntesTosmp(int idTest, int idSample);

    TestDto AddAndAsignTestToSample(TestDto ws, int idSample);
}
