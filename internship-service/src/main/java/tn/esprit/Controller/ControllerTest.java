package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Subject;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.IServiceTest;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Test")
public class ControllerTest {
    IServiceTest ist;


    @DeleteMapping("/delete/{id}")
    public void deleteTest(@PathVariable("id") Integer idTest)
    {
        ist.DeleteTest(idTest);
    }
    @GetMapping("/all")
    public List<Test> getAllTest() {
        return ist.getAllTest();
    }

    @GetMapping("get/{id}")
    public Test getTest(@PathVariable("id") Integer idTest)
    {
        return ist.findTest(idTest);
    }
    @PutMapping("/createTest/{followUpSheetid}")
    public Test affecttesttofollowupsheet(@PathVariable("followUpSheetid") int followUpSheetid,@RequestBody Test test){
        return ist.affecttesttofollowupsheet(followUpSheetid, test);
    }

    @GetMapping("/getresult/{testId}")
    public int getresult(@PathVariable("testId") int testId, @RequestBody List<String> internAnswers) {
        return ist.getresult(testId,internAnswers);
    }

    @PostMapping("addTest")
    public Test addTest(@RequestBody Test test)
    {
        return ist.addTest(test);
    }
}
