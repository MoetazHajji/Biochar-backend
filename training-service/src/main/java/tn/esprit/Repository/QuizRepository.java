package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    @Query("select case when count(q) > 0 then true else false end from Quiz q where q.question=?1")
    Boolean checkIfQuestionExists(String question);
}
