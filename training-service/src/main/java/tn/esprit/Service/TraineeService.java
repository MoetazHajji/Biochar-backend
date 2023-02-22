package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Quiz;
import tn.esprit.Entity.Trainee;
import tn.esprit.Interface.ITraineeService;
import tn.esprit.Repository.TraineeRepository;
import tn.esprit.Repository.TrainingRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    public int submit_Answer(List<Integer> answers, Long id_trainee) {

        Trainee trainee = traineeRepository.findById(id_trainee).orElse(null);
        if(trainee.getValidate_day() !=null) {
            Set<Quiz> quizzes = trainee.getTraining().getQuizes();
            int score = 100;
            float fault_cost = 100 / quizzes.size();
            int counter = 0;
            for (Quiz quiz : quizzes) {
                if (quiz.getValid_answer() != answers.get(counter))
                    score -= fault_cost;
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
