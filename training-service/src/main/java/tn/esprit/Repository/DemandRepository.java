package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Demand;

import java.util.List;

public interface DemandRepository extends JpaRepository<Demand,Long> {
    @Query("SELECT d from Demand d order by d.date_d")
    List<Demand> getAllDemands();
}
