package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Question;

public interface RepoQuestion extends JpaRepository<Question,Integer> {
}
