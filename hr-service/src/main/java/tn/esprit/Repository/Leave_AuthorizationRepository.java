package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Leave_Authorization;

import java.util.Date;
import java.util.List;

@Repository
public interface Leave_AuthorizationRepository extends JpaRepository<Leave_Authorization, Long> {

   @Query("select lv from Leave_Authorization lv where lv.state_la = tn.esprit.Entity.State_LA.Accepted and lv.type_la = tn.esprit.Entity.Type_LA.Leave")
   public List<Leave_Authorization> getLeave_AuthorizationsByStateAndType();
   public List<Leave_Authorization> findLeave_AuthorizationsByAccount_Id(Long idA);
   @Query("select lv from Leave_Authorization lv where lv.start_date >= :startDate and lv.end_date <= :endDate ")
   public List<Leave_Authorization> findLeave_AuthorizationsByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
   @Query("select count(lv) from Leave_Authorization lv where lv.account.id = ?1")
   public Long nbLeaveAndAuth(Long id);
   /*@Query("select lv.remaining_days from Leave_Authorization lv where lv.end_date = (select max(lv1.end_date) from Leave_Authorization lv1) and lv.state_la = tn.esprit.Entity.State_LA.Archived and lv.account.id = ?1")
   public Long getLatestRemainingDaysByAccount(Long idA);*/
}