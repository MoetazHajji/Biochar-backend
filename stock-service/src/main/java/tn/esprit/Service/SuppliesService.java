package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Supplies;
import tn.esprit.Interface.ISuppliesService;
import tn.esprit.Repository.ISuppliesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SuppliesService implements ISuppliesService {
    ISuppliesRepository suppliesRepository;

    @Override
    public Supplies addSupplie(Supplies supplies) {
        return suppliesRepository.save(supplies);
    }

    @Override
    public Supplies modifySupplie(Supplies supplies) {
        return suppliesRepository.save(supplies);
    }

    @Override
    public void deleteSupplie(Long id) {
        suppliesRepository.deleteById(id);
    }

    @Override
    public Supplies getSupplieById(Long id) {
        return suppliesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Supplies> getAllSupplies() {
        List<Supplies> suppliesList = new ArrayList<>();
        suppliesRepository.findAll().forEach(suppliesList::add);
        return suppliesList;
    }
}
