package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Supplies;

@Repository
public interface ISuppliesRepository extends JpaRepository<Supplies,Long> {
}
