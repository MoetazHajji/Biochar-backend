package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.OfferCall;

@Repository
public interface IOfferCallRepository extends JpaRepository<OfferCall,Long> {
}
