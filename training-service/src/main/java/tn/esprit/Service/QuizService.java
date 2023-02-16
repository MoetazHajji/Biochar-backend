package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Quiz;
import tn.esprit.Interface.IQuizService;
import tn.esprit.Repository.QuizRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizService implements IQuizService {
    final QuizRepository quizRepository;

    @Override
    public Quiz add_quiz(Quiz q) {
        return quizRepository.save(q);
    }

    @Override
    public void delete_quiz(Long id) {
         quizRepository.deleteById(id);
    }

    @Override
    public List<Quiz> getAll_quiz() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getById_quiz(Long id) {
        return quizRepository.findById(id).orElse(null);
    }
}
