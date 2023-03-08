package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Entity.Account;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Exception.ElementNotFoundException;
import tn.esprit.Interface.IMedicalcard;
import tn.esprit.Mappers.MedicalcardMapper;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.MedicalcardRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalcardService implements IMedicalcard {

    private final MedicalcardRepository medicalcardRepository;
    private final AccountRepository accountRepository;

private final Path root= Paths.get("uploads");

    @Override
    public Medicalcard addOrUpdateMedicalcard( Medicalcard medicalcard) {
        //Medicalcard medicalcard= medicalcardRepository.save(medicalcard);

            return medicalcardRepository.save(medicalcard);

    }

    @Override
    public void removeMedicalcard(int idMedicalcard) {
        medicalcardRepository.deleteById(idMedicalcard);
    }

    @Override
    public Medicalcard retriveMedicalcard(int idMedicalcard) {
        return medicalcardRepository.findById(idMedicalcard).orElseThrow(() -> new ElementNotFoundException("Medical card with id "+ idMedicalcard +" not found : " ));
    }
    @Override
    public List<MedicalcardDto> retrieveAllMedicalcards() {

       // List<Medicalcard> medicalcards =new ArrayList<>();
        return  medicalcardRepository.findAll().stream().map(medicalcard -> MedicalcardMapper.mapToDto(medicalcard)).collect(Collectors.toList());
        // medicalcards;
    }

    @Override
    public void init()
    {
        try{
        Files.createDirectories( root);
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(MultipartFile file) {
        init();
     try {
         Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
     }catch (IOException e){
         throw new RuntimeException(e);
     }
     }

    @Override
    public Resource load(String filename) {
        Path file = root.resolve(filename);
        Resource resource= null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if(resource.exists() || resource.isReadable()){
            return resource;
        }
        else{
            throw new RuntimeException("ne peut pas lire ce fichier");
        }

    }

    @Override
    public Stream<Path> loadAll() {

        try {
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root)).map(this.root::relativize) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Medicalcard asignDepToEt(Integer idMedicalcard, Long id) {
        Medicalcard e = medicalcardRepository.findById(idMedicalcard).orElse(null);
        Account d = accountRepository.findById(id).orElse(null);
        e.setAccount(d);

        return medicalcardRepository.save(e);
    }
}
