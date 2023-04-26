package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.FollowUpSheet;
import tn.esprit.Entity.Option;
import tn.esprit.Entity.Question;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.IServiceTest;
import tn.esprit.Repository.RepoFollowUpSheet;
import tn.esprit.Repository.RepoOption;
import tn.esprit.Repository.RepoQuestion;
import tn.esprit.Repository.RepoTest;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiceTest implements IServiceTest {
    RepoTest rt;
    RepoFollowUpSheet rfs;
    RepoOption ro;
    RepoQuestion rq;

    @Override
    public void DeleteTest(Integer idTest) {
        rt.deleteById(idTest);

    }

    @Override
    public List<Test> getAllTest() {
        List<Test> TestList = new ArrayList<>();
        rt.findAll().forEach(TestList::add);
        return TestList;
    }

    @Override
    public Test findTest(Integer idTest) {
        return rt.findById(idTest).orElse(null);
    }

    @Override
    public Test affecttesttofollowupsheet(int followUpSheetid, int idtest) {
        Test test=rt.findById(idtest).orElse(null);
        FollowUpSheet followUpSheet = rfs.findById(followUpSheetid)
                .orElse(null);
        test.setFollowUpSheet(followUpSheet);

        return rt.save(test);
    }

    @Override
    public int getresult(int testid, List<String> internAnswers) {
        Test test=rt.findById(testid).orElse(null);
        int score = 0;
        List<Question> questions = test.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            List<Option> options = question.getOptions();
            String employeeAnswer = internAnswers.get(i);
            for (Option option : options) {
                if (option.getCorrect() && employeeAnswer.equals(option.getText())) {
                    score++;
                    break;
                }
            }
        }
        FollowUpSheet followUpSheet = rfs.retrieveByTest(testid);
        followUpSheet.getScores().add(score);
        return score;
    }

    @Override
    public Test addTest(Test test) {
        for (Question question : test.getQuestions()) {
            question.setTest(test);

            for (Option option : question.getOptions()) {
                option.setQuestion(question);

            }
        }
        return rt.save(test);
    }
}