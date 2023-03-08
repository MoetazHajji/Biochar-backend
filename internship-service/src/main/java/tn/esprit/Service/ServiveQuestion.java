package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Question;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.IServiceQuestion;
import tn.esprit.Repository.RepoQuestion;
import tn.esprit.Repository.RepoTest;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiveQuestion implements IServiceQuestion {
    RepoQuestion rq;
    RepoTest rt;

    @Override
    public Question addQuestion(Question q, int testid) {
        Test t = rt.findById(testid).orElse(null);
        q.setTest(t);
        return rq.save(q);

    }

    @Override
    public void DeleteQuestion(Integer idQuestion) {
        rq.deleteById(idQuestion);

    }

    @Override
    public List<Question> getAllQuestion() {
        List<Question> QuestionList = new ArrayList<>();
        rq.findAll().forEach(QuestionList::add);
        return QuestionList;
    }

    @Override
    public Question findQuestion(Integer idQuestion) {
        return rq.findById(idQuestion).orElse(null);
    }
}
