package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("select sum(pr.price) from Product pr")
    Double getSumtotale();
}
