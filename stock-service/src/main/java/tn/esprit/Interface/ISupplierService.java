package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Supplier;

import java.util.List;

@Service
public interface ISupplierService {
    Supplier addSupplier(Supplier supplier);
    Supplier modifySupplier(Supplier supplier);
    void deleteSupplier(Long id);
    Supplier getSupplierById(Long id);
    List<Supplier> getAllSuppliers();
    Supplier AddSupplierWithAdresses(Supplier supplier);
}
