package tn.esprit.Interface;

import tn.esprit.Entity.Internship;
import tn.esprit.Entity.Test;

import java.util.Date;
import java.util.List;

public interface IServiceInternship {
    Internship addInternship(Internship i,int request_id);

    void DeleteInternship(Integer idInternship);

    List<Internship> getAllInternship();

    Internship findInternship(Integer idInternship);

    Test findtestbydate(Date dateoftheday);
}
