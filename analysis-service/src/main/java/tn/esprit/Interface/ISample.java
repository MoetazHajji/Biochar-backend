package tn.esprit.Interface;

import tn.esprit.Dto.SampleDto;
import tn.esprit.Entity.Sample;

import java.util.List;

public interface ISample {


    SampleDto addOrUpdateSample(SampleDto sampleDto);

    void removeSample(int idSample);

    Sample retriveSample(int idSample);


    List<SampleDto> retrieveAllSample();

    Sample asignSamTopat(int idSample, Long id);

    SampleDto AddAndAsignsampleToAccount(SampleDto ws, Long id);
}
