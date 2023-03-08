package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.FollowUpSheet;
import tn.esprit.Entity.Test;

import java.util.List;

public interface RepoFollowUpSheet extends JpaRepository<FollowUpSheet,Integer> {


    @Query("select f from FollowUpSheet f join f.tests t where t.id =?1")
    FollowUpSheet retrieveByTest(int id);

}
