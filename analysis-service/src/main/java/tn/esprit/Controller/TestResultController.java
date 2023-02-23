package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Sample;
import tn.esprit.Entity.Test;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.ITestResult;

import java.io.IOException;
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
    @PostMapping("/readPDF")
    List<TestResult> readPDFtest(@RequestBody MultipartFile file){
        try {
            return iTestResult.readTests(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PutMapping("/asign/{idTestResult}/{idTest}")
    TestResult ModifierDep (@PathVariable ("idTestResult") Integer idTestResult, @PathVariable("idTest") Integer idTest){
        return iTestResult.asigntesTosmp(idTestResult,idTest);

    }
    @GetMapping("/gett/{id}")
    TestResult affichBYAcounnt(@PathVariable("id") int id){
        return iTestResult.retriveTestResultBYAcounnt(id);
    }
}
