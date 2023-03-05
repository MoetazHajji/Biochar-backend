package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Adress;
import tn.esprit.Interface.IAdressService;
import tn.esprit.Repository.IAdressRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AdressService implements IAdressService {
    IAdressRepository adressRepository;
    @Override
    public Adress addAdress(Adress adress) {
        return adressRepository.save(adress);
    }

    @Override
    public Adress modifyAdress(Adress adress) {
        return adressRepository.save(adress);
    }

    @Override
    public void deleteAdresse(Long id) {
        adressRepository.deleteById(id);
    }

    @Override
    public Adress getAdressById(Long id) {
        return adressRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Adress> getAllAdresses() {
        Set<Adress> adressList = new HashSet<>();
        adressRepository.findAll().forEach(adressList::add);
        return adressList;
    }
}
