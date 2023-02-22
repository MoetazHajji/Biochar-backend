package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Trainer;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainerService;
import tn.esprit.Repository.TrainerRepository;
import tn.esprit.Repository.TrainingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerService implements ITrainerService {
    final TrainerRepository trainerRepository;
    final TrainingRepository trainingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Trainer add_trainer(Trainer t) {
        if(t.getTrainings() != null) {
            Set<Training> trainings = t.getTrainings();
           // t.setTrainings(null);
            t =  trainerRepository.save(t);

            for(Training training : trainings)
            {
                training.setTrainer(t);
                trainingRepository.save(training);
            }

            return trainerRepository.findById(t.getId()).orElse(null);
        }
        return  trainerRepository.save(t);
    }

    @Override
    public void delete_trainer(Long id) {
           Training training = trainingRepository.get_training_by_trainerId(id).orElse(null);
           if(training != null)
           {
               training.setTrainer(null);

               trainingRepository.save(training);
           }
               trainerRepository.deleteById(id);

    }

    @Override
    public List<Trainer> getAll_trainer() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getById_trainer(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete_all() {
        trainerRepository.deleteAll();
    }
}
