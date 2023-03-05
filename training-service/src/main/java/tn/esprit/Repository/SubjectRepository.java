package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Subject;

import java.util.Optional;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findFirstByTitle(String title);

    @Query("select count(s) from Subject s")
    Integer getNumber();
}
