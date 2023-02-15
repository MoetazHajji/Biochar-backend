package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Command;

@Repository
public interface ICommandRepository extends JpaRepository<Command,Long> {
}
