package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Account;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAccountsByDateCreationEquals(Date date);

}