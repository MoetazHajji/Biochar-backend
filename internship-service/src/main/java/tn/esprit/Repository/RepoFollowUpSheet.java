package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.FollowUpSheet;

public interface RepoFollowUpSheet extends JpaRepository<FollowUpSheet,Integer> {
}
