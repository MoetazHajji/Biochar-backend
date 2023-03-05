package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Supplier;
import tn.esprit.Interface.IAdressService;
import tn.esprit.Interface.ISupplierService;
import tn.esprit.Repository.IAdressRepository;
import tn.esprit.Repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SupplierService implements ISupplierService {
    ISupplierRepository supplierRepository;
    IAdressRepository adressRepository;
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
    public Set<Supplier> getAllSuppliers() {
        Set<Supplier> supplierList = new HashSet<>();
        supplierRepository.findAll().forEach(supplierList::add);
        return supplierList;
    }

    @Override
    public Supplier AddSupplierWithAdresses(Supplier supplier) {
        supplierRepository.save(supplier);
        Set<Adress> adressList = supplier.getAdresses();
        supplier.setAdresses(null);
        for(Adress adress:adressList){
            adress.setSupplier(supplier);
            adressRepository.save(adress);
        }
        return supplier;
    }
}
