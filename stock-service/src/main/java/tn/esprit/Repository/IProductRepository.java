package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.Entity.Product;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
