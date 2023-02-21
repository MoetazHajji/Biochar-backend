package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Training;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training,Long> {
   @Query("select t from Training t order by t.start_date")
    List<Training> getAllSortedByDate();

    @Query("select t from Training t order by t.duration")
    List<Training> getAllSortedByDuration();

    @Query("select t from Training t order by size(t.reviews)")
    List<Training> getAllSortedByReviews();



}
