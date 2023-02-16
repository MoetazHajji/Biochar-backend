package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Entity.Sample;

public interface SampleRepository extends JpaRepository<Sample,Integer> {
}
