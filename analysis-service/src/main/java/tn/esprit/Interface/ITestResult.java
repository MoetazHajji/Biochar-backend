package tn.esprit.Interface;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ITestResult {

    TestResult addOrUpdateTestResult(TestResult testResult);

    void removeSTestResult(int idTestResult);

    TestResult retriveTestResult(int idTestResult);

    List<TestResult> retrieveAllTestResult();

    List<TestResult> readTests(MultipartFile file) throws IOException;

    TestResult asigntesTosmp(int idTestResult, int idTest);

    List<TestResult>  retriveTestResultBYAcounnt(Long id);

    TestResult retriveTestResult(String resultat);

    long countttt();

    long counttt();

    long countt();

    TestResult cou();

    Map<Integer,String[]> divide_table(String table);
}
