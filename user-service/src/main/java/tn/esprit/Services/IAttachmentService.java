package tn.esprit.Services;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Attachment;

public interface IAttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;
    Attachment getAttachment(String fileId) throws Exception;
    Attachment savePhotoAccount(MultipartFile file, Long idAccount ) throws Exception;
}
