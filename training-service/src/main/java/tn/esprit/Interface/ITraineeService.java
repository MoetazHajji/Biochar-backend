package tn.esprit.Interface;

import tn.esprit.Entity.Trainee;

import java.util.List;

public interface ITraineeService {
    Trainee add_trainee(Trainee t);
    void delete_trainee(Long id);
    List<Trainee> getAll_trainee();
    Trainee getById_trainee(Long id);
}
