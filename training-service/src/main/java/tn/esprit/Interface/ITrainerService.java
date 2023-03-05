package tn.esprit.Interface;

import tn.esprit.Entity.Trainer;

import java.util.List;

public interface ITrainerService {
    Trainer add_trainer(Trainer t);
    void delete_trainer(Long id);
    List<Trainer> getAll_trainer();
    Trainer getById_trainer(Long id);
    void delete_all();
}
