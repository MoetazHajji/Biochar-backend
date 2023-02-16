package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Supplier;
import tn.esprit.Interface.ISupplierService;
import tn.esprit.Repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService implements ISupplierService {
    ISupplierRepository supplierRepository;
    @Override
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier modifySupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        supplierRepository.findAll().forEach(supplierList::add);
        return supplierList;
    }
}
