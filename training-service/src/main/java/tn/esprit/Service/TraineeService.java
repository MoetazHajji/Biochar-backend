package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.*;
import tn.esprit.External.Profile;
import tn.esprit.Interface.ITraineeService;
import tn.esprit.Repository.ProfileRepository;
import tn.esprit.Repository.SubjectRepository;
import tn.esprit.Repository.TraineeRepository;
import tn.esprit.Repository.TrainingRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class TraineeService implements ITraineeService {
    final TraineeRepository traineeRepository;
    final TrainingRepository trainingRepository;

    final SubjectRepository subjectRepository;

    final ProfileRepository profileRepository;

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
            if(size>0) {
                float cost = 100 / size;
                for (Map.Entry<String, List<Integer>> answer : answers.entrySet()) {
                    Quiz quiz = null;
                    for (Quiz quiz1 : quizzes) {
                        if (quiz1.getQuestion().trim().equalsIgnoreCase(answer.getKey()))
                            quiz = quiz1;
                    }
                    if (quiz != null) {
                        if (quiz.getType_q() == Type_Q.QCU) {
                            if (quiz.getValid_answer().get(0) == answer.getValue().get(0))
                                score += cost;
                        } else {
                            Boolean check = true;
                            if (quiz.getValid_answer().size() > answers.size())
                                check = false;
                            else {
                                for (int i = 0; i < quiz.getValid_answer().size(); i++) {
                                    if (quiz.getValid_answer().get(i) != answer.getValue().get(i))
                                        check = false;
                                }

                            }
                            if (check)
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
            else
                return -1;
        }
        return -2;
    }

    @Override
    public Map<String,List<Training>> get_suits(Long profile_id) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Training> trainings = trainingRepository.findByStartdateAfter(date);
        Profile profile = profileRepository.findById(profile_id).orElse(null);
        double profile_score = calculate_profile_score(profile,date);

        Map<String,List<Training>> filtered_trainings = new HashMap<>();
        List<Training> trainings1 = new ArrayList<>();
        List<Training> trainings2 = new ArrayList<>();
        filtered_trainings.put("internal",trainings1);
        filtered_trainings.put("external",trainings2);
        for(Training training: trainings) {

                if (training.getType_t() == Type_T.internal) {
                    Subject subject = subjectRepository.retrieveByTitle(training.getTitle().trim().toLowerCase()).orElse(null);
                    if (subject != null) {
                        if (subject.getComplexity() == (int) profile_score / 10) {
                            filtered_trainings.get("internal").add(training);
                        }
                    } else {
                        training.setType_t(Type_T.external);
                        filtered_trainings.get("external").add(training);
                        trainingRepository.save(training);
                    }
                } else
                    filtered_trainings.get("external").add(training);
            }

        return filtered_trainings;
    }


    private double calculate_profile_score(Profile profile,Date date)
    {
        double knowledge_score = 0;
        double trainings_score = 0;
        List<Subject> subjects = subjectRepository.findAll();
        int size = 0;
        if(subjects.size() >0) {

            double knowledge_crit = 20 / subjects.size();

            for (Subject subject : subjects) {
                String knowledge = profile.getKnowledge() + " " + profile.getSkills() ;
                if (knowledge.contains(subject.getTitle()))
                    knowledge_score += knowledge_crit;
            }

            List<Trainee> trainees = traineeRepository.findByEmail(profile.getEmail());
            for (Trainee trainee : trainees) {
                if (!(trainee.getValidate_day() == null && trainee.getTraining().getEnddate().after(date))) {
                    trainings_score += trainee.getScore();
                    size++;
                }
            }
        }
        else
            knowledge_score = 0;
        if(size > 10)
            trainings_score /=size;
        else if (size <10 && size>0)
            trainings_score /= size*((1-(size/10))+1);
        else
            trainings_score = 0;
        int experience = profile.getExperience();
        if(experience > 10)
            experience = 10;
<<<<<<< HEAD

        return knowledge_score + trainings_score*0.6+experience*2;
=======
        return knowledge_score + trainings_score*0.6+experience;
>>>>>>> 102443cc9b44e3437356c17fdebd41b29388e138

    }

    @Override
    public double getScore(Long id_profile)
    {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Profile profile =profileRepository.findById(id_profile).orElse(null);
        if(profile !=null)
            return calculate_profile_score(profile,date);
        return 0;
    }

}
