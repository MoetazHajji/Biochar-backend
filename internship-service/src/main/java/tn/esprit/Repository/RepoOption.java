package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Option;

public interface RepoOption extends JpaRepository<Option,Integer> {
}
