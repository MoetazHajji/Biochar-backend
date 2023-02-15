package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Detail;
import tn.esprit.Interface.IServiceDetail;
import tn.esprit.Repository.RepoDetail;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiceDetail implements IServiceDetail {
    RepoDetail rd;
    @Override
    public Detail addDetail(Detail d) {
        return rd.save(d);
    }
    @Override
    public Detail updateDetail(Detail d) {
        return rd.save(d);
    }
    @Override
    public void DeleteDetail(Integer idDetail) {
        rd.deleteById(idDetail);

    }
    @Override
    public List<Detail> getAllDetail() {
        List<Detail> DetailList = new ArrayList<>();
        rd.findAll().forEach(DetailList::add);
        return DetailList;
    }

    @Override
    public Detail findDetail(Integer idDetail) {
        return rd.findById(idDetail).orElse(null);
    }













}
