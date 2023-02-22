package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.CertificateDto;
import tn.esprit.Entity.Certificate;
import tn.esprit.Interface.ICertificateService;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Mapper.CertificateMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("certificate")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CertificateController {
    final ICertificateService certificateService;
    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public CertificateDto add_certifcate(@RequestBody CertificateDto c)
    {

        return CertificateMapper
                .mapToDto(certificateService
                        .add_certificate(CertificateMapper
                                .mapToEntity(c,trainingService)));

    }

    @DeleteMapping("{id}")
    public void delete_certificate(@PathVariable("id") Long id)
    {
        certificateService.delete_certificate(id);
    }

    @GetMapping
    public List<CertificateDto> getAll_certificate()
    {
       List<CertificateDto> certificateDtos = new ArrayList<>();
        certificateService.getAll_certificate().forEach(certificate -> certificateDtos.add(CertificateMapper.mapToDto(certificate)));
        return certificateDtos;
    }

    @GetMapping("/{id}")
    public CertificateDto getById_certificate(@PathVariable("id") Long id)
    {
        return CertificateMapper.mapToDto(certificateService.getById_certificat(id));
    }

    @DeleteMapping("all")
    public void delete_all()
    {
       certificateService.delete_all();
    }
}
