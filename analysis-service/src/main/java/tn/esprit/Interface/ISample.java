package tn.esprit.Interface;

import tn.esprit.Entity.Sample;

import java.util.List;

public interface ISample {
    Sample addOrUpdateSample(Sample sample);

    void removeSample(int idSample);

    Sample retriveSample(int idSample);

    List<Sample> retrieveAllSample();
}
