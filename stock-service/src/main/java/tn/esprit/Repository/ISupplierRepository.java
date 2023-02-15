package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier,Long> {
}
