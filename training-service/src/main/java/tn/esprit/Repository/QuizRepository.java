package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
