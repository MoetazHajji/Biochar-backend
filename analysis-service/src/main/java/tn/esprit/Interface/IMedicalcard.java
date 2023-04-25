package tn.esprit.Interface;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Entity.Medicalcard;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface IMedicalcard {

    Medicalcard addOrUpdateMedicalcard(Medicalcard medicalcard);

    void removeMedicalcard(int idMedicalcard);

    Medicalcard retriveMedicalcard(int idMedicalcard);


    List<MedicalcardDto> retrieveAllMedicalcards();

    public void init() throws IOException;
    public void save(MultipartFile file);
    public Resource load(String filename);
    public Stream<Path> loadAll();
    Medicalcard asignDepToEt(Integer idMedicalcard, Long id);
}
