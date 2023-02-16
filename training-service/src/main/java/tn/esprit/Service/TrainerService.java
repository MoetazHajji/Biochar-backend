package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Trainer;
import tn.esprit.Interface.ITrainerService;
import tn.esprit.Repository.TrainerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerService implements ITrainerService {
    final TrainerRepository trainerRepository;

    @Override
    public Trainer add_trainer(Trainer t) {
        return trainerRepository.save(t);
    }

    @Override
    public void delete_trainer(Long id) {
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
}
