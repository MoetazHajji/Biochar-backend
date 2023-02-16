package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Training;

public interface TrainingRepository extends JpaRepository<Training,Long> {
}
