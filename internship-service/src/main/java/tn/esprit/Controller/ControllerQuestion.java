package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Option;
import tn.esprit.Entity.Question;
import tn.esprit.Interface.IServiceQuestion;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Question")
public class ControllerQuestion {
    IServiceQuestion isq;

    @PutMapping("/update/{testid}")
    public Question updateQuestion(@RequestBody Question q,@PathVariable("testid") Integer testid) {
        return isq.addQuestion(q,testid);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable("id") Integer idQuestion)
    {
        isq.DeleteQuestion(idQuestion);
    }

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return isq.getAllQuestion();
    }

    @GetMapping("get/{id}")
    public Question getQuestion(@PathVariable("id") Integer idQuestion)
    {
        return isq.findQuestion(idQuestion);
    }
}
