package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Entity.TypeDossier;
import tn.esprit.Interface.IMedicalcard;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Medicalcard")
@RequiredArgsConstructor
public class MedicalcardController {
    private final IMedicalcard iMedicalcard;

    @PostMapping("/add/{inputString}")
    MedicalcardDto addMedicalcard(@RequestBody MedicalcardDto medicalcardDto, @PathVariable String inputString){
        TypeDossier inputEnum = TypeDossier.valueOf(inputString.toUpperCase());

        if (inputEnum.compareTo(TypeDossier.NEW) == 0) {
            // execute function 1
            return iMedicalcard.addOrUpdateMedicalcard(medicalcardDto);
        } else {
            // execute function 2
            return null;
        }

    }
   @DeleteMapping("/delete/{idMedicalcard}")
    void deleteEtudiant(@PathVariable("idMedicalcard") Integer idMedicalcard){
        iMedicalcard.removeMedicalcard(idMedicalcard);
    }
     @PutMapping("/update")
     MedicalcardDto updateEtudiant(@RequestBody MedicalcardDto e){
        return iMedicalcard.addOrUpdateMedicalcard(e);
    }
     @GetMapping("/get/{idMedicalcard}")
     Medicalcard getStudent(@PathVariable("idMedicalcard") Integer idMedicalcard){
         return iMedicalcard.retriveMedicalcard(idMedicalcard);
     }
     @GetMapping("/all")
     List<MedicalcardDto> getAllMedicalcard(){
         return iMedicalcard.retrieveAllMedicalcards();
     }
    @PostMapping("/envoyer")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
    iMedicalcard.save(file);
    return ResponseEntity.ok("fichier enregistré avec secsee"+file.getOriginalFilename());
}
    @GetMapping("/files")
public ResponseEntity<List<Medicalcard>> getAlldocs(){
        List<Medicalcard> medicalcards = iMedicalcard.loadAll().map(path -> {
            String fileName=path.getFileName().toString();
            String Doc = MvcUriComponentsBuilder.fromMethodName(MedicalcardController.class,"getFile",
                    path.getFileName().toString()).build().toString();
            return new Medicalcard(fileName,Doc);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(medicalcards);
    }
    @GetMapping("/files/{filename:.+}")
    public  ResponseEntity<?> getFile(@PathVariable String filename){
        Resource file = iMedicalcard.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment: filename= \""+file.getFilename()+"\"").body(file);
    }
}


