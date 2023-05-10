package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Internship;
import tn.esprit.Entity.InternshipRequest;

import java.util.List;

public interface RepoInternshipRequest extends JpaRepository<InternshipRequest,Integer> {

    List<InternshipRequest> findAll();
}
