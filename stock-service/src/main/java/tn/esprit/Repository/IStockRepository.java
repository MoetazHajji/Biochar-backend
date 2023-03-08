package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Stock;
import tn.esprit.Entity.Type_product;

@Repository
public interface IStockRepository extends JpaRepository<Stock,Long> {
    @Query("select count(pr) from Stock st join st.products pr where st.id = :id")
    Long NbProductsInStock(@Param("id") Long id);

    @Query("select st from Stock st where st.type_product=:type_product")
    Stock getStockByType_product(@Param("type_product") Type_product type_product);

    @Query("select pr.size_product from Stock st join st.products pr where st.id = :id")
    Double getSumSizeOfProducts(@Param("id") Long id);

    @Query("SELECT  stock.free_storage FROM Stock stock where stock.free_storage=0 " )
    boolean isFullStock();

    @Query("select pr.quantity from Stock st join st.products pr where st.id = :id")
    Double getTotalQantity(@Param("id") Long id);
    @Query("select pr from Stock  st join st.products pr where st.id =:id ")
    Product getProductFromById(@Param("id") Long id);

     @Query("UPDATE Stock  st SET st.total_quantity = st.total_quantity - :quantity   where st.id = :id")
    void withdrawProductFromStock(@Param("quantity") Double quantity,@Param("id") Long id);

     @Query("UPDATE Stock  st SET st.free_storage = st.free_storage + :quantity  where st.id = :id")
    void withdrawFstProductFromStock(@Param("quantity") Double quantity,@Param("id")  Long id);

    @Query("UPDATE Stock  st SET st.used_storage = st.used_storage - :quantity  where st.id = :id")
    void withdrawUstProductFromStock(@Param("quantity")Double quantity,@Param("id") Long id);
}
