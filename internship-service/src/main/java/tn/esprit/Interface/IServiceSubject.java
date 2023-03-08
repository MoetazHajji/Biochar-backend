package tn.esprit.Interface;

import tn.esprit.Entity.Internship;
import tn.esprit.Entity.Subject;

import java.util.List;

public interface IServiceSubject {
    Subject addSubject(Subject s);

    void DeleteSubject(Integer idSubject);

    List<Subject> getAllSubject();

    Subject findSubject(Integer idSubject);
}
