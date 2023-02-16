package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Certificate;
import tn.esprit.Interface.ICertificateService;
import tn.esprit.Repository.CertificateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateService implements ICertificateService {
     final CertificateRepository certificateRepository;

     @Override
     public Certificate add_certificate(Certificate c) {
          return certificateRepository.save(c);
     }

     @Override
     public void delete_certificate(Long id) {
          certificateRepository.deleteById(id);
     }

     @Override
     public List<Certificate> getAll_certificate() {
          return certificateRepository.findAll();
     }

     @Override
     public Certificate getById_certificat(Long id) {
          return certificateRepository.findById(id).orElse(null);
     }
}
