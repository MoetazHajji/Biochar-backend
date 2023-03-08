package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Demand;
import tn.esprit.Interface.IDemandService;
import tn.esprit.Repository.DemandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DemandService implements IDemandService {
    final DemandRepository demandRepository;



    @Override
    public Demand add_demand( Demand d) {

        return demandRepository.save(d);
    }

    @Override
    public void delete_demand(Long id) {
       demandRepository.deleteById(id);
    }

    @Override
    public List<Demand> getAll_demands() {
        return demandRepository.getAllDemands();
    }

    @Override
    public void delete_all() {
        demandRepository.deleteAll();
    }
}
