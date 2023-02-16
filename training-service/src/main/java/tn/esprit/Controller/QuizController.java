package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Quiz;
import tn.esprit.Interface.IQuizService;


import java.util.List;

@RestController
@RequestMapping("quiz")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class QuizController {
    final IQuizService quizService;


    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz add_quiz(@RequestBody Quiz q)
    {

        return quizService.add_quiz(q);
    }

    @DeleteMapping("{id}")
    public void delete_quiz(@PathVariable("id") Long id)
    {
        quizService.delete_quiz(id);
    }

    @GetMapping
    public List<Quiz> getAll_quiz()
    {

        return quizService.getAll_quiz();
    }

    @GetMapping("/{id}")
    public Quiz getById_quiz(@PathVariable("id") Long id)
    {
        return quizService.getById_quiz(id);
    }
}
