package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.ITestResult;
import tn.esprit.Repository.TestResultRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class TestResultService implements ITestResult {

    private final TestResultRepository testResultRepository;



    @Override
    public TestResult addOrUpdateTestResult(TestResult testResult) {
        return testResultRepository.save(testResult);
    }

    @Override
    public void removeSTestResult(int idTestResult) {
        testResultRepository.deleteById(idTestResult);
    }

    @Override
    public TestResult retriveTestResult(int idTestResult) {
        return testResultRepository.findById(idTestResult).orElse(null);
    }
    @Override
    public List<TestResult> retrieveAllTestResult() {

        List<TestResult> TestResults =new ArrayList<>();
        testResultRepository.findAll().forEach(TestResults::add);
        return TestResults;
    }
}
