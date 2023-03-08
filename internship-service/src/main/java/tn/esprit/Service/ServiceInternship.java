package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Internship;
import tn.esprit.Entity.InternshipRequest;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.IServiceInternship;
import tn.esprit.Repository.RepoInternship;
import tn.esprit.Repository.RepoInternshipRequest;
import tn.esprit.Repository.RepoTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ServiceInternship implements IServiceInternship {
    RepoInternship ri;
    RepoInternshipRequest rir;
    RepoTest rt;
    @Override
    public Internship addInternship(Internship i,int request_id) {
        InternshipRequest ir = rir.findById(request_id).orElse(null);
        i.setInternshipRequest(ir);
       return ri.save(i);

    }

    @Override
    public void DeleteInternship(Integer idInternship) {
        ri.deleteById(idInternship);

    }
    @Override
    public List<Internship> getAllInternship() {
        List<Internship> InternshipList = new ArrayList<>();
        ri.findAll().forEach(InternshipList::add);
        return InternshipList;
    }
    @Override
    public Internship findInternship(Integer idInternship) {
        return ri.findById(idInternship).orElse(null);
    }
     @Override
    public Test findtestbydate(Date dateoftheday){

         Test t = null;
         for(Test test : rt.findAll()) {
             if (test.getDate().getTime() == dateoftheday.getTime())
                 t = test;
         }
       return t;
    }
}
