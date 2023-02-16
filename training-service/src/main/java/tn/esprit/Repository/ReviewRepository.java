package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
