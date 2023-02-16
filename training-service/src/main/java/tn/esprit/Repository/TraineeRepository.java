package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee,Long> {
}
