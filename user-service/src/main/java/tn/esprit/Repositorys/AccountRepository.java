package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> { }
