package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Command;

@Repository
public interface ICommandRepository extends JpaRepository<Command,Long> {

    @Query("select sum(pr.price)  FROM Command cm join cm.commandLignes cl join cl.product pr where cm.id= :id")
    Double calculSumPriceProducts(Long id);

    @Query("select count(pr) from Command cm join cm.commandLignes cl join cl.product pr where cm.id = :id")
    Long getNumberProducts(Long id);

    @Query("select sum(cl.quantite_product) from Command cm join cm.commandLignes cl  where cm.id= :id")
    Long TotalQuantityOfProducts(Long id);

}
