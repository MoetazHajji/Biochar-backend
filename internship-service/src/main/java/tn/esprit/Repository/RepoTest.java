package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Test;

import java.util.Date;
import java.util.Optional;

public interface RepoTest extends JpaRepository<Test,Integer> {


}
