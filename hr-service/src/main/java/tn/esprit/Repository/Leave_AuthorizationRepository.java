package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Leave_Authorization;

public interface Leave_AuthorizationRepository extends JpaRepository<Leave_Authorization, Long> {

   public Leave_Authorization findByAccount_Id(Long idAccount);

}