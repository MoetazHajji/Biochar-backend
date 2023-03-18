package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.SampleDto;
import tn.esprit.Entity.Account;
import tn.esprit.Entity.Sample;
import tn.esprit.Interface.ISample;
import tn.esprit.Mappers.SampleMapper;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.SampleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleService implements ISample {

    private final SampleRepository sampleRepository;
    private final AccountRepository accountRepository;

    @Override
    public SampleDto addOrUpdateSample(SampleDto sampleDto) {
        sampleDto.setDate(LocalDate.now());
        Sample sample= sampleRepository.save(SampleMapper.mapToEntity(sampleDto));

        return SampleMapper.mapToDto(sample);
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
    public List<SampleDto> retrieveAllSample() {

        return  sampleRepository.findAll().stream().map(sample -> SampleMapper.mapToDto(sample)).collect(Collectors.toList());

    }
    @Override
    public Sample asignSamTopat(int idSample, Long id) {
        Account d = accountRepository.findById(id).orElse(null);
        Sample e = sampleRepository.findById(idSample).orElse(null);
        e.setAccount(d);

        return sampleRepository.save(e);
    }
    @Override
    public SampleDto AddAndAsignsampleToAccount(SampleDto ws, Long id) {
        Sample sample = SampleMapper.mapToEntity(ws);
        sample = sampleRepository.save(sample);
        Account a = accountRepository.findById(id).orElse(null);
        sample.setAccount(a);
        sampleRepository.save(sample);
        return SampleMapper.mapToDto(sample);
    }

}
