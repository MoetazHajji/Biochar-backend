package tn.esprit.Interface;

import tn.esprit.Entity.TestResult;

import java.util.List;

public interface ITestResult {

    TestResult addOrUpdateTestResult(TestResult testResult);

    void removeSTestResult(int idTestResult);

    TestResult retriveTestResult(int idTestResult);

    List<TestResult> retrieveAllTestResult();
}
