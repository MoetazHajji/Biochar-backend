package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Adress;

@Repository
public interface IAdressRepository extends JpaRepository<Adress,Long> {
}
