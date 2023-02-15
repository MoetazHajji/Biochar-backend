package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.Entity.Supplier;

public interface ISupplierRepository extends JpaRepository<Supplier,Long> {
}
