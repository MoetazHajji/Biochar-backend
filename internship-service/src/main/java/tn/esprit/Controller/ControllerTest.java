package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Subject;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.IServiceTest;

import javax.ws.rs.QueryParam;
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
    @PutMapping("aff")
    public Test affect(@QueryParam("followU pSheetid") int followUpSheetid, @QueryParam("idtest") int idtest){
        return ist.affecttesttofollowupsheet(followUpSheetid, idtest);
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
