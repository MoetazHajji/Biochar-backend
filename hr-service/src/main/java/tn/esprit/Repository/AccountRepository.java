package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.Entity.ExternelEntity.Roles;
import tn.esprit.Entity.Shift;
import tn.esprit.Entity.ExternelEntity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAccountsByShift(Shift shift);

    @Query("select ac from  Account ac where ((ac.role = :role)and(ac.shift in (tn.esprit.Entity.Shift.Afternoon,tn.esprit.Entity.Shift.Morning))) order by ac.cin asc ")
    List<Account> findDayShiftAccounts(@Param("role") Roles role );


}