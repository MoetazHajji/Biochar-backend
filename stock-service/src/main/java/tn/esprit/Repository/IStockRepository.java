package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Stock;
import tn.esprit.Entity.Type_product;

@Repository
public interface IStockRepository extends JpaRepository<Stock,Long> {
    @Query("select count(pr) from Stock st join st.products pr where st.id = :id")
    Long NbProductsInStock(Long id);

    @Query("select st from Stock st where st.type_product=:type_product")
    Stock getStockByType_product(Type_product type_product);

    @Query("select sum (pr.size_product) from Stock st join st.products pr where st.id = :id")
    Double getSumSizeOfProducts(Long id);

}
