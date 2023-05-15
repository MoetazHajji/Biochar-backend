package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Internship;
import tn.esprit.Entity.InternshipRequest;
import tn.esprit.Interface.IServiceInternshipRequest;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/InternshipRequest")
public class ControllerInternshipRequest {
    IServiceInternshipRequest isir;


    @DeleteMapping("/delete/{id}")
    public void deleteInternshipRequest(@PathVariable("id") Integer idInternshipRequest)
    {
        isir.DeleteInternshipRequest(idInternshipRequest);
    }
    @GetMapping("/all")
    public List<InternshipRequest> getAllInternshipRequest() {
        return isir.getAllInternshipRequest();
    }

    @GetMapping("get/{id}")
    public InternshipRequest getInternshipRequest(@PathVariable("id") Integer idInternshipRequest)
    {
        return isir.findInternshipRequest(idInternshipRequest);
    }

    @PutMapping("add_request")
    public InternshipRequest add_request_with_cv(@RequestPart InternshipRequest ir, @RequestPart MultipartFile cv)
    {
        return isir.add_request_with_cv(ir,cv);
    }

/*    @PutMapping("/add_request")
    public InternshipRequest add_request_with_cv(@RequestBody InternshipRequest ir)
    {
        return isir.add_request_with_cv(ir);
    }*/
}
