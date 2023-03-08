package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Offer;

@Repository
public interface IOfferCallRepository extends JpaRepository<Offer,Long> {
}
