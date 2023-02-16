package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Interface.IMedicalcard;
import tn.esprit.Repository.MedicalcardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalcardService implements IMedicalcard {

    private final MedicalcardRepository medicalcardRepository;



    @Override
    public Medicalcard addOrUpdateMedicalcard(Medicalcard medicalcard) {
        return medicalcardRepository.save(medicalcard);
    }

    @Override
    public void removeMedicalcard(int idMedicalcard) {
        medicalcardRepository.deleteById(idMedicalcard);
    }

    @Override
    public Medicalcard retriveMedicalcard(int idMedicalcard) {
        return medicalcardRepository.findById(idMedicalcard).orElse(null);
    }
    @Override
    public List<Medicalcard> retrieveAllMedicalcards() {

        List<Medicalcard> medicalcards =new ArrayList<>();
        medicalcardRepository.findAll().forEach(medicalcards::add);
        return medicalcards;
    }
}
