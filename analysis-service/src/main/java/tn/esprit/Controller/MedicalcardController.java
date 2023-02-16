package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Interface.IMedicalcard;

import java.util.List;

@RestController
@RequestMapping("Medicalcard")
@RequiredArgsConstructor
public class MedicalcardController {
    private final IMedicalcard iMedicalcard;
    @PostMapping("/add")
    Medicalcard addMedicalcard(@RequestBody Medicalcard medicalcard){
        return iMedicalcard.addOrUpdateMedicalcard(medicalcard);
    }
    @DeleteMapping("/delete/{idMedicalcard}")
    void deleteEtudiant(@PathVariable("idMedicalcard") Integer idMedicalcard){
        iMedicalcard.removeMedicalcard(idMedicalcard);
    }
    @PutMapping("/update")
    Medicalcard updateEtudiant(@RequestBody Medicalcard e){
        return iMedicalcard.addOrUpdateMedicalcard(e);
    }
    @GetMapping("/get/{idMedicalcard}")
    Medicalcard getStudent(@PathVariable("idMedicalcard") Integer idMedicalcard){
        return iMedicalcard.retriveMedicalcard(idMedicalcard);
    }
    @GetMapping("/all")
    List<Medicalcard> getAllMedicalcard(){
        return iMedicalcard.retrieveAllMedicalcards();
    }
}
