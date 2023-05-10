package tn.esprit.Controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.Dto.AttachementDto;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Mappers.AttachmentMapper;
import tn.esprit.Services.IAttachmentService;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    private IAttachmentService attachmentService;

    public AttachmentController(IAttachmentService   attachmentService) {
        this.attachmentService = attachmentService;

    }



    @PostMapping("/upload")//(@RequestParam("file")MultipartFile[]files)
    public AttachementDto uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = attachmentService.saveAttachment(file);
        return  AttachmentMapper.mapToDto( attachment  );
    }
    @PutMapping ("/upload-to-Account/{idAccount}")//(@RequestParam("file")MultipartFile[]files)
    public AttachementDto uploadPhotoAccount(@RequestParam("file")MultipartFile file, @PathVariable("idAccount")   Long idAccount) throws Exception {
        Attachment attachment = attachmentService.savePhotoAccount(file,idAccount);
        return  AttachmentMapper.mapToDto( attachment  );
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
