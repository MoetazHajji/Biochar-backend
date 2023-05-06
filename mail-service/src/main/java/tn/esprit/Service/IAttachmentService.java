package tn.esprit.Service;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Models.Attachment;

public interface IAttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;
    Attachment getAttachment(String fileId) throws Exception;
    Attachment savePhotoAccount(MultipartFile file, Long idAccount ) throws Exception;
}
