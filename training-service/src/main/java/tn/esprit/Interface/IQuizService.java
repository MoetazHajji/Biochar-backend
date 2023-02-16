package tn.esprit.Interface;

import tn.esprit.Entity.Quiz;

import java.util.List;

public interface IQuizService {
    Quiz add_quiz(Quiz q);
    void delete_quiz(Long id);
    List<Quiz> getAll_quiz();
    Quiz getById_quiz(Long id);
}
