package tn.esprit.Interface;

import tn.esprit.Entity.Question;

import java.util.List;

public interface IServiceQuestion {


    Question addQuestion(Question q, int testid);

    void DeleteQuestion(Integer idQuestion);

    List<Question> getAllQuestion();

    Question findQuestion(Integer idQuestion);
}
