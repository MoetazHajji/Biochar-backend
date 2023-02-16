package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Medicalcard;

public interface MedicalcardRepository extends JpaRepository<Medicalcard,Integer> {

}
