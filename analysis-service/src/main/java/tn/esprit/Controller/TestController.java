package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.TestDto;
import tn.esprit.Entity.Sample;
import tn.esprit.Entity.Test;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.ITest;
import tn.esprit.Interface.ITestResult;

import java.util.List;

@RestController
@RequestMapping("Test")
@RequiredArgsConstructor
public class TestController {

    private final ITest iTest;

    @PostMapping("/add")
    TestDto addtest(@RequestBody TestDto e){
        return iTest.addOrUpdateTest(e);
    }

    @DeleteMapping("/delete/{idTest}")
    void deletetest(@PathVariable("idTest") int idTest){
        iTest.removeSTest(idTest);
    }

    @PutMapping("/update")
    TestDto updateTest(@RequestBody TestDto e){
        return iTest.addOrUpdateTest(e);
    }

    @GetMapping("/get/{idTest}")
    Test affichtest(@PathVariable("idTest") int idTest){
        return iTest.retriveTest(idTest);
    }
    @GetMapping("/all")
    List<TestDto> getAllTest(){
        return iTest.retrieveAllTest();
    }

    @PutMapping("/asign/{idTest}/{idSample}")
    Test ModifierDep (@PathVariable ("idTest") Integer idTest, @PathVariable("idSample") Integer idSample){
        return iTest.asigntesTosmp(idTest,idSample);

    }
}
