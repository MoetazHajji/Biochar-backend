package tn.esprit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dto.AttachementDto;
import tn.esprit.Mappers.AttachmentMapper;
import tn.esprit.Models.Attachment;
import tn.esprit.Models.Msg;
import tn.esprit.Service.ISMTPMail;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private ISMTPMail mailSender;

    @GetMapping("/all")
    public String all(){
        return "all";
    }
    @PostMapping("/simple-mail")
    public ResponseEntity<String> simpleMail(@RequestBody  Msg msg){
        try {
            mailSender.connect();
            mailSender.sendingSimple(msg);
            } catch (   MessagingException e) {
              throw new RuntimeException(e);
            }
        return ResponseEntity.ok( "sending OK");
    }
    @PostMapping("/mail-file-stream")
    public ResponseEntity<String> mailFileStream(@RequestBody  Msg msg){
        try {
            mailSender.sendingWithStreamDocuments(msg);
        } catch ( MessagingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok( "sending OK");
    }





 /*   @PutMapping ("/upload-to-Account/{idAccount}")//(@RequestParam("file")MultipartFile[]files)
    public AttachementDto uploadPhotoAccount(@RequestParam("file") MultipartFile file, @PathVariable("idAccount")   Long idAccount) throws Exception {
        Attachment attachment = attachmentService.savePhotoAccount(file,idAccount);
        return  AttachmentMapper.mapToDto( attachment  );
        return null;
    }*/
  /*  @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }*/
}
