package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Trainee;
import tn.esprit.Interface.ITraineeService;
import tn.esprit.Repository.TraineeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TraineeService implements ITraineeService {
    final TraineeRepository traineeRepository;

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
}
