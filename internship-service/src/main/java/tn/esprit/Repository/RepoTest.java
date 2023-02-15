package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Test;

public interface RepoTest extends CrudRepository<Test,Integer> {
}
