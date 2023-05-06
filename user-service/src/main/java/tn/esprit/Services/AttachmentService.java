package tn.esprit.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.AttachmentRepository;
import tn.esprit.exception.RessourceNotFoundException;


@Service
public class AttachmentService implements IAttachmentService{

    private AttachmentRepository attachmentRepository;
    @Autowired
    private AccountRepository accountRepository;
    public AttachmentService(AttachmentRepository attachmentRepository,AccountRepository accountRepository) {
        this.attachmentRepository = attachmentRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getSize(),
                    file.getBytes()
                   );
            return attachmentRepository.save(attachment);



       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
       }
    }
    @Override
    @Transactional
    public Attachment savePhotoAccount(MultipartFile file, Long idAccount ) throws Exception {
        Account account = accountRepository.findById(idAccount).  orElseThrow(()-> new RessourceNotFoundException("Service Account : delete Account not existe with id : "+idAccount)) ;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment attachment = new Attachment(fileName,file.getContentType(),file.getSize(),file.getBytes());
            account.setAttachment(attachment);
            account =  accountRepository.save(account);
            String fileId = account.getAttachment().getId();
            attachment = attachmentRepository.findById(fileId).orElseThrow(() -> new Exception("File not found with Id: " + fileId));
            return attachment;
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
