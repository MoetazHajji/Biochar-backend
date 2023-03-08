package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Internship;
import tn.esprit.Entity.Subject;
import tn.esprit.Interface.IServiceSubject;
import tn.esprit.Repository.RepoInternship;
import tn.esprit.Repository.RepoSubject;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiceSubject implements IServiceSubject {
    RepoSubject rs;
    RepoInternship ri;
    @Override
    public Subject addSubject(Subject s) {
        return rs.save(s);
    }

    @Override
    public void DeleteSubject(Integer idSubject) {
        rs.deleteById(idSubject);

    }
    @Override
    public List<Subject> getAllSubject() {
        List<Subject> SubjectList = new ArrayList<>();
        rs.findAll().forEach(SubjectList::add);
        return SubjectList;
    }
    @Override
    public Subject findSubject(Integer idSubject) {
        return rs.findById(idSubject).orElse(null);
    }
}
