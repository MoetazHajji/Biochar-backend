package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Test;

public interface RepoTest extends JpaRepository<Test,Integer> {
}
