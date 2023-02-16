package tn.esprit.Interface;

import tn.esprit.Entity.Certificate;

import java.util.List;

public interface ICertificateService {
    Certificate add_certificate(Certificate c);
    void delete_certificate(Long id);
    List<Certificate> getAll_certificate();
    Certificate getById_certificat(Long id);

}
