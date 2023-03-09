package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Certificate;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ICertificateService;
import tn.esprit.Repository.CertificateRepository;
import tn.esprit.Repository.TrainingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class CertificateService implements ICertificateService {
     final CertificateRepository certificateRepository;

     final TrainingRepository trainingRepository;
     @Override
     @Transactional
     public Certificate add_certificate(Certificate c) {



          Long rand_nbr = c.getNumber();
          if(c.getId() ==null) {
               Random rand = new Random();
                rand_nbr = Math.abs(rand.nextLong());
          }
          else
          {
          rand_nbr =  certificateRepository.findById(c.getId()).orElse(null).getNumber();
          }
          c.setNumber(rand_nbr);
          Training t = c.getTraining();
          Certificate certificate = certificateRepository.save(c);
          t.setCertificate(certificate);
          trainingRepository.save(t);
          certificate.setTraining(t);
          return certificate;
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

     @Override
     public void delete_all() {
          certificateRepository.deleteAll();
     }


}
