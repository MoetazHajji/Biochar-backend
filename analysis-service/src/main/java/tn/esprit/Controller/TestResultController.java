package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.ITestResult;

import java.util.List;

@RestController
@RequestMapping("TestResult")
@RequiredArgsConstructor
public class TestResultController {
    private final ITestResult iTestResult;

    @PostMapping("/add")
    TestResult addtestResult(@RequestBody TestResult e){
        return iTestResult.addOrUpdateTestResult(e);
    }
    @DeleteMapping("/delete/{idTestResult}")
    void deleteResult(@PathVariable("idTestResult") int idTestResult){
        iTestResult.removeSTestResult(idTestResult);
    }
    @PutMapping("/update")
    TestResult updateTestResult(@RequestBody TestResult e){
        return iTestResult.addOrUpdateTestResult(e);
    }

    @GetMapping("/get/{idTestResult}")
    TestResult affichtestResul(@PathVariable("idTestResult") int idTestResult){
        return iTestResult.retriveTestResult(idTestResult);
    }
    @GetMapping("/all")
    List<TestResult> getAllSample(){
        return iTestResult.retrieveAllTestResult();
    }
}
