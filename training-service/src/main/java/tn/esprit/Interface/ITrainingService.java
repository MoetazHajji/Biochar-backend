package tn.esprit.Interface;

import tn.esprit.Entity.Training;

import java.util.List;

public interface ITrainingService {
    Training add_training(Training t);
    void delete_training(Long id);
    List<Training> getAll_training();
    Training getById_training(Long id);


}
