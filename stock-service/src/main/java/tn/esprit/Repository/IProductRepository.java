package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Type_product;

import java.util.List;
import java.util.Set;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("select pr from Product pr where pr.type_product= :type_product")
    Product getProductByProductType(@Param("type_product") Type_product type_product);

    @Query("select pr from Product pr where pr.reference= :refrence")
    List<Product> findProduc(@Param("refrence") String refrence);

    @Query("select pr from Product pr where pr.name_product = :name")
    List<Product> getListProductByName(@Param("name") String name);

    @Query("SELECT pr FROM Product pr ORDER BY pr.count_order desc ")
    List<Product> FindAllProductsByOrderCountDesc();

    @Query("select pr from Product pr join pr.commandLignes lg where lg.command.id = :id ")
    Set<Product> findProductsByCommandId(@Param("id") Long commandId);

}
