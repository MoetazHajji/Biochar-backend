package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Type_product;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("select pr from Product pr where pr.type_product= :type_product")
    Product getProductByProductType(Type_product type_product);
}
