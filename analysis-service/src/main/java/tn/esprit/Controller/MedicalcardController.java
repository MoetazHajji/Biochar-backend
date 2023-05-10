package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Interface.IMedicalcard;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Medicalcard")
@RequiredArgsConstructor
public class MedicalcardController {
    private final IMedicalcard iMedicalcard;

    @PostMapping("/add/NEW")
    Medicalcard addMedicalcard(@RequestBody Medicalcard medicalcard ){
            return iMedicalcard.addOrUpdateMedicalcard(medicalcard);

    }

    @PostMapping("/add/OLD")
    ResponseEntity<String>  addMedicalcard( @RequestParam MultipartFile file ){
            iMedicalcard.save(file);
            return ResponseEntity.ok("fichier enregistré avec secsee"+file.getOriginalFilename());

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
     List<MedicalcardDto> getAllMedicalcard(){
         return iMedicalcard.retrieveAllMedicalcards();
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

    @PutMapping("/asignmedtoacc/{idMedicalcard}/{id}")
    Medicalcard ModifierDep (@PathVariable ("idMedicalcard") Integer idMedicalcard, @PathVariable("id") Long id){
        return iMedicalcard.asignDepToEt(idMedicalcard,id);

    }
    @PostMapping("/AddAndAssignmedicalToAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicalcardDto AddAndAssignMeToAccount(@RequestBody MedicalcardDto medicalcardDto, @PathVariable("id") Long id){
        return iMedicalcard.AddAndAsignmedicalToAccount(medicalcardDto,id);
    }
}


