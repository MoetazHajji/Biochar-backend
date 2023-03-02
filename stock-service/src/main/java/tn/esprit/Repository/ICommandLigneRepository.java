package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.CommandLigne;

@Repository
public interface ICommandLigneRepository extends JpaRepository<CommandLigne,Long> {
}
