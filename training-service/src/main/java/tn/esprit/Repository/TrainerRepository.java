package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
}
