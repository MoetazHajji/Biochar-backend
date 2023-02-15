package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Internship;

public interface RepoInternship extends CrudRepository<Internship,Integer> {
}
