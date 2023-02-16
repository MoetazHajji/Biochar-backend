package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Sample;
import tn.esprit.Interface.ISample;
import tn.esprit.Repository.SampleRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class SampleService implements ISample {

    private final SampleRepository sampleRepository;



    @Override
    public Sample addOrUpdateSample(Sample sample) {
        return sampleRepository.save(sample);
    }

    @Override
    public void removeSample(int idSample) {
        sampleRepository.deleteById(idSample);
    }

    @Override
    public Sample retriveSample(int idSample) {
        return sampleRepository.findById(idSample).orElse(null);
    }
    @Override
    public List<Sample> retrieveAllSample() {

        List<Sample> Samples =new ArrayList<>();
        sampleRepository.findAll().forEach(Samples::add);
        return Samples;
    }
}
