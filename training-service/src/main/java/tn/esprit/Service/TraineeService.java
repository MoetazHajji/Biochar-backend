package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Quiz;
import tn.esprit.Entity.Trainee;
import tn.esprit.Entity.Type_Q;
import tn.esprit.Interface.ITraineeService;
import tn.esprit.Repository.TraineeRepository;
import tn.esprit.Repository.TrainingRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TraineeService implements ITraineeService {
    final TraineeRepository traineeRepository;
    private final TrainingRepository trainingRepository;

    @Override
    public Trainee add_trainee(Trainee t) {
        return traineeRepository.save(t);
    }

    @Override
    public void delete_trainee(Long id) {
        traineeRepository.deleteById(id);
    }

    @Override
    public List<Trainee> getAll_trainee() {
        return traineeRepository.findAll();
    }

    @Override
    public Trainee getById_trainee(Long id) {
        return traineeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete_all() {
        traineeRepository.deleteAll();
    }

    @Override
    public int submit_Answer(Map<String,List<Integer>> answers, Long id_trainee) {

        Trainee trainee = traineeRepository.findById(id_trainee).orElse(null);
        if(trainee.getValidate_day() ==null) {
            Set<Quiz> quizzes = trainee.getTraining().getQuizes();
            int score = 0;
            int size = quizzes.size();
            float cost = 100 / size;
            for(Map.Entry<String,List<Integer>> answer :answers.entrySet())
            {
                 Quiz quiz = null;
                 for(Quiz quiz1 : quizzes)
                 {
                     if(quiz1.getQuestion().trim().equalsIgnoreCase(answer.getKey()))
                         quiz = quiz1;
                 }
                 if(quiz !=null)
                 {
                     if(quiz.getType_q() == Type_Q.QCU)
                     {
                         if(quiz.getValid_answer().get(0) == answer.getValue().get(0))
                             score +=cost;
                     }
                     else
                     {
                         Boolean check = true;
                         if(quiz.getValid_answer().size()>answers.size())
                             check = false;
                         else  {
                            for(int i =0;i<quiz.getValid_answer().size();i++)
                            {
                                if(quiz.getValid_answer().get(i) != answer.getValue().get(i))
                                    check = false;
                            }

                         }
                         if(check)
                             score += cost;
                     }
                 }

            }

            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            trainee.setValidate_day(date);
            trainee.setScore(score);
            traineeRepository.save(trainee);
            return score;
        }
        return -1;
    }
}
