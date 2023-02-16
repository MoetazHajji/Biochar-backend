package tn.esprit.Interface;

import tn.esprit.Entity.Medicalcard;

import java.util.List;

public interface IMedicalcard {
    Medicalcard addOrUpdateMedicalcard(Medicalcard medicalcard);

    void removeMedicalcard(int idMedicalcard);

    Medicalcard retriveMedicalcard(int idMedicalcard);

    List<Medicalcard> retrieveAllMedicalcards();
}
