package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.Entity.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    @Query("SELECT r from Request r order by r.date_r")
    List<Request> getAllRequests();
}
