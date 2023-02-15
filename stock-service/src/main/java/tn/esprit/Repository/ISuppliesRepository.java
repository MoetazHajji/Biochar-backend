package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.Entity.Supplies;

public interface ISuppliesRepository extends JpaRepository<Supplies,Long> {
}
