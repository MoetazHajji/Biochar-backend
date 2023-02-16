package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.InternshipRequest;

public interface RepoInternshipRequest extends JpaRepository<InternshipRequest,Integer> {
}
