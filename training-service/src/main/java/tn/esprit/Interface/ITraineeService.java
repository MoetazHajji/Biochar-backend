package tn.esprit.Interface;

import tn.esprit.Entity.Trainee;

import java.util.List;
import java.util.Map;

public interface ITraineeService {
    Trainee add_trainee(Trainee t);
    void delete_trainee(Long id);
    List<Trainee> getAll_trainee();
    Trainee getById_trainee(Long id);
    void delete_all();
    int submit_Answer(Map<String,List<Integer>> answers, Long id_trainee);
}
