package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Adress;

public interface ICommandRepository extends JpaRepository<Adress,Long> {
}
