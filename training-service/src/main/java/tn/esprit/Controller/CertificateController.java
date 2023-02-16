package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Certificate;
import tn.esprit.Interface.ICertificateService;

import java.util.List;

@RestController
@RequestMapping("certificate")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CertificateController {
    final ICertificateService certificateService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Certificate add_certifcate(@RequestBody Certificate c)
    {
        return certificateService.add_certificate(c);
    }

    @DeleteMapping("{id}")
    public void delete_certificate(@PathVariable("id") Long id)
    {
        certificateService.delete_certificate(id);
    }

    @GetMapping
    public List<Certificate> getAll_certificate()
    {
       return certificateService.getAll_certificate();
    }

    @GetMapping("/{id}")
    public Certificate getById_certificate(@PathVariable("id") Long id)
    {
        return certificateService.getById_certificat(id);
    }
}
