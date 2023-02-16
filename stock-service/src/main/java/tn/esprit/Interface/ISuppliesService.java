package tn.esprit.Interface;


import org.springframework.stereotype.Service;
import tn.esprit.Entity.Supplies;

import java.util.List;

@Service
public interface ISuppliesService {
    Supplies addSupplie(Supplies supplies);
    Supplies modifySupplie(Supplies supplies);
    void deleteSupplie(Long id);
    Supplies getSupplieById(Long id);
    List<Supplies> getAllSupplies();

}
