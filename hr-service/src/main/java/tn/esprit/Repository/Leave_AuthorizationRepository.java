package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Leave_Authorization;

public interface Leave_AuthorizationRepository extends CrudRepository<Leave_Authorization, Long> {

   public Leave_Authorization findByAccount_Id(Long idAccount);

}