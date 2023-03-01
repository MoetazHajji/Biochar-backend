package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Trainer;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {

}
