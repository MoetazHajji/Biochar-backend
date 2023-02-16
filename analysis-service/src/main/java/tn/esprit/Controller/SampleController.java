package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Medicalcard;
import tn.esprit.Entity.Sample;
import tn.esprit.Interface.IMedicalcard;
import tn.esprit.Interface.ISample;

import java.util.List;

@RestController
@RequestMapping("Sample")
@RequiredArgsConstructor
public class SampleController {
    private final ISample iSample;
    @PostMapping("/add")
    Sample addSample(@RequestBody Sample sample){
        return iSample.addOrUpdateSample(sample);
    }
    @DeleteMapping("/delete/{idSample}")
    void deleteSample(@PathVariable("idSample") Integer idSample){
        iSample.removeSample(idSample);
    }
    @PutMapping("/update")
    Sample updateSample(@RequestBody Sample e){
        return iSample.addOrUpdateSample(e);
    }
    @GetMapping("/get/{idSample}")
    Sample getSample(@PathVariable("idSample") Integer idSample){
        return iSample.retriveSample(idSample);
    }
    @GetMapping("/all")
    List<Sample> getAllSample(){
        return iSample.retrieveAllSample();
    }
}
