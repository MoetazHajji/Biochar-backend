package tn.esprit.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Models.Attachment;
import tn.esprit.exception.RessourceNotFoundException;


@Service
public class AttachmentService implements IAttachmentService{



    public AttachmentService( ) {

    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName);
            }

            Attachment attachment = new Attachment(fileName,  file.getContentType(),  file.getSize(),  file.getBytes()  );
            return   attachment ;



       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
       }
    }
    @Override
    @Transactional
    public Attachment savePhotoAccount(MultipartFile file, Long idAccount ) throws Exception {
     return null;
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        /*return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));*/
        return null;
    }
}
