package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Certificate;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Repository.CertificateRepository;
import tn.esprit.Repository.TrainerRepository;
import tn.esprit.Repository.TrainingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingService implements ITrainingService {
     final TrainingRepository trainingRepository;

     final CertificateRepository certificateRepository;
     private final TrainerRepository trainerRepository;

     @Override
     public Training add_training(Training t) {
          return trainingRepository.save(t);
     }

     @Override
     public void delete_training(Long id) {
        trainingRepository.deleteById(id);
     }

     @Override
     public List<Training> getAll_training() {
          return trainingRepository.findAll();
     }

     @Override
     public Training getById_training(Long id) {
          return trainingRepository.findById(id).orElse(null);
     }


}
