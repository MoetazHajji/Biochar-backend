package tn.esprit.Interface;

<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;
=======
import tn.esprit.Entity.TestResult;
>>>>>>> 41213c82bd231b05c1cac2c0506ff883f71b1048

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
