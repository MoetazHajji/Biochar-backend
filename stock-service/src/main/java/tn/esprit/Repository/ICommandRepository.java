package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Command;

@Repository
public interface ICommandRepository extends JpaRepository<Command,Long> {

    @Query("select count(pr) * sum (pr.price) FROM Command cm join cm.products pr where cm.id= :id")
    Double calculSumPriceProducts(Long id);
}
