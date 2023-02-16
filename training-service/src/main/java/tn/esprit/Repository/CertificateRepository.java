package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
}
