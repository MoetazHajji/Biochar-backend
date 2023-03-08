package tn.esprit.Interface;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.InternshipRequest;

import java.util.List;

public interface IServiceInternshipRequest {


    InternshipRequest addInternshipRequest(InternshipRequest ir);

    void DeleteInternshipRequest(Integer idInternshipRequest);

    List<InternshipRequest> getAllInternshipRequest();

    InternshipRequest findInternshipRequest(Integer idInternshipRequest);

    InternshipRequest add_request_with_cv(InternshipRequest ir,MultipartFile cv);

}
