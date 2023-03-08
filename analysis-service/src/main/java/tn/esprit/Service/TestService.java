package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.TestDto;
import tn.esprit.Entity.Sample;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.ITest;
import tn.esprit.Mappers.TestMapper;
import tn.esprit.Repository.SampleRepository;
import tn.esprit.Repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
    @RequiredArgsConstructor
    @Slf4j

    public class TestService implements ITest {

        private final TestRepository testRepository;
    private final SampleRepository sampleRepository;



        @Override
        public TestDto addOrUpdateTest(TestDto testDto) {
            Test test= testRepository.save(TestMapper.mapToEntity(testDto));

            return TestMapper.mapToDto(test);
        }

        @Override
        public void removeSTest(int idTest) {
            testRepository.deleteById(idTest);
        }

        @Override
        public Test retriveTest(int idTest) {
            return testRepository.findById(idTest).orElse(null);
        }
        @Override
        public List<TestDto> retrieveAllTest() {

            return  testRepository.findAll().stream().map(test -> TestMapper.mapToDto(test)).collect(Collectors.toList());

        }
    @Override
    public Test asigntesTosmp(int idTest, int idSample) {
        Test d = testRepository.findById(idTest).orElse(null);
        Sample e = sampleRepository.findById(idSample).orElse(null);
        d.setSample(e);

        return testRepository.save(d);
    }
}
