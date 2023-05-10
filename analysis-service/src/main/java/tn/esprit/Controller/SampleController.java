package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.SampleDto;
import tn.esprit.Entity.Sample;
import tn.esprit.Interface.ISample;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Sample")
@RequiredArgsConstructor
public class SampleController {
    private final ISample iSample;
    @PostMapping("/add")
    SampleDto addSample(@RequestBody SampleDto sample){
        return iSample.addOrUpdateSample(sample);
    }
    @DeleteMapping("/delete/{idSample}")

    void deleteSample(@PathVariable("idSample") Integer idSample){
        iSample.removeSample(idSample);
    }
    @PutMapping("/update")

    SampleDto updateSample(@RequestBody SampleDto e){
        return iSample.addOrUpdateSample(e);
    }
    @GetMapping("/get/{idSample}")

    Sample getSample(@PathVariable("idSample") Integer idSample){
        return iSample.retriveSample(idSample);
    }
    @GetMapping("/all")

    List<SampleDto> getAllSample(){
        return iSample.retrieveAllSample();
    }

    @PutMapping("/asign/{idSample}/{id}")
    Sample ModifierDep (@PathVariable ("idSample") Integer idSample,@PathVariable("id") Long id){
        return iSample.asignSamTopat(idSample,id);

    }

    @PostMapping("/AddAndAssignSapleToAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SampleDto AddAndAssignSapleToAccount(@RequestBody SampleDto sampleDto, @PathVariable("id") Long id){
        return iSample.AddAndAsignsampleToAccount(sampleDto,id);
    }
}
