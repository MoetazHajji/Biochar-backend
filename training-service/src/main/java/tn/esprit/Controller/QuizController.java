package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.QuizDto;
import tn.esprit.Entity.Quiz;
import tn.esprit.Interface.IQuizService;
import tn.esprit.Mapper.QuizMapper;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("quiz")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class QuizController {
    final IQuizService quizService;


    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public QuizDto add_quiz(@RequestBody QuizDto q)
    {
        return QuizMapper
                .mapToDto(quizService
                        .add_quiz(QuizMapper
                                .mapToEntity(q)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void delete_quiz(@PathVariable("id") Long id)
    {
        quizService.delete_quiz(id);
    }

    @GetMapping
    public List<QuizDto> getAll_quiz()
    {
        List<QuizDto> quizDtos = new ArrayList<>();
        quizService.getAll_quiz()
                .forEach(quiz -> quizDtos.add(QuizMapper.mapToDto(quiz)));
        return quizDtos;
    }

    @GetMapping("/{id}")
    public QuizDto getById_quiz(@PathVariable("id") Long id)
    {

        return QuizMapper.mapToDto(quizService.getById_quiz(id));
    }

    @DeleteMapping("all")
    public void delete_all()
    {
        quizService.delete_all();
    }
}
