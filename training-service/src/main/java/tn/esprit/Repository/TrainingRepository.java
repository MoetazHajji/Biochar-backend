package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Training;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training,Long> {
   @Query("select t from Training t order by t.start_date")
    List<Training> getAllSortedByDate();

    @Query("select t from Training t order by t.duration")
    List<Training> getAllSortedByDuration();

    @Query("select t from Training t order by size(t.reviews)")
    List<Training> getAllSortedByReviews();

    Optional<Training> findByTitle(String title);

    @Query("select tr from Training t join  t.trainer tr where tr.firstname =?1 and tr.lastname = ?2")
    List<Training> getTrainingByTrainer(String firstname, String lastname);

    @Query("select t from Training t where t.trainer.id = ?1")
    Optional<Training> get_training_by_trainerId(Long id);


    @Query("select t from Training t join t.reviews r where t.end_date<?1 and t.trainer.email = ?2 ")
    List<Training> getTrainingsByEmailAndDate(Date date,String email);
}
