package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Internship;

import java.util.List;

public interface RepoInternship extends JpaRepository<Internship,Integer> {

    List<Internship> findAll();
}
