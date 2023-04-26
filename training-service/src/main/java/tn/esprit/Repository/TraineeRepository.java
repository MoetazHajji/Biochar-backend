package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Trainee;
import tn.esprit.Entity.Training;

import java.util.List;
import java.util.Optional;

public interface TraineeRepository extends JpaRepository<Trainee,Long> {
    List<Trainee> findByEmail(String email);

    @Query("select t from Training t join t.trainees tr where t.title = ?1 and tr.email = ?2")
    Optional<Training> getByTrainingAndTrainee(String title,String email);
}
