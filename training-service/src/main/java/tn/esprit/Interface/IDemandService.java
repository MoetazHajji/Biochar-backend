package tn.esprit.Interface;

import tn.esprit.Entity.Demand;

import java.util.List;

public interface IDemandService {
    Demand add_demand(Demand r);
    void delete_demand(Long id);
    List<Demand> getAll_demands();

    void delete_all();
}
