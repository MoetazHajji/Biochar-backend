package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Subject;

import java.util.Optional;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("select s from Subject  s where lower(s.title)=?1")
    Optional<Subject> retrieveByTitle(String title);

    @Query("select count(s) from Subject s")
    Integer getNumber();
}
