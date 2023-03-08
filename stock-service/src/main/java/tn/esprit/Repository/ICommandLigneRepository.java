package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.CommandLigne;

@Repository
public interface ICommandLigneRepository extends JpaRepository<CommandLigne,Long> {
     @Query("select sum(cl.quantite_product) from CommandLigne cl join cl.product pr where pr.id = :id")
    Double SumOfProductQuantity(Long id);
}
