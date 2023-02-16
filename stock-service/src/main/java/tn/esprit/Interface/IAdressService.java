package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Adress;

import java.util.List;

@Service
public interface IAdressService {
    Adress addAdress(Adress adress);
    Adress modifyAdress(Adress adress);
    void deleteAdresse(Long id);
    Adress getAdressById(Long id);
    List<Adress> getAllAdresses();
}
