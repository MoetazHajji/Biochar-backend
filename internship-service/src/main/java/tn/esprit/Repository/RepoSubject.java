package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Subject;

public interface RepoSubject extends CrudRepository<Subject,Integer> {
}
