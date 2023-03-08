package tn.esprit.Interface;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.TestResult;

import java.io.IOException;
import java.util.List;

public interface ITestResult {

    TestResult addOrUpdateTestResult(TestResult testResult);

    void removeSTestResult(int idTestResult);

    TestResult retriveTestResult(int idTestResult);

    List<TestResult> retrieveAllTestResult();

    List<TestResult> readTests(MultipartFile file) throws IOException;

    TestResult asigntesTosmp(int idTestResult, int idTest);

    TestResult retriveTestResultBYAcounnt(int id);
}
