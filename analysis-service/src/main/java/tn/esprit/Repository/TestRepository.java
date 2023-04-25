package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Test;

public interface TestRepository extends JpaRepository<Test,Integer> {
   // List<Test> getTestsByDateTest
}
