package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entitys.Account;
import tn.esprit.Models.Msg;
import tn.esprit.Services.IEmailSenderService;

import javax.mail.MessagingException;

@Controller
@RestController
@RequestMapping("/EmailSender")
public class EmailSenderController {
    private IEmailSenderService IEmailSenderService;
    @Autowired
    public EmailSenderController(@Qualifier("EmailSender") IEmailSenderService IEmailSenderService)
    {this.IEmailSenderService = IEmailSenderService;}



    @PostMapping("/SimpleEmail")
    public String SendSimpleEmail(@RequestBody Msg msg ) {  return IEmailSenderService.SendSimpleEmail(   msg);  }

    @PostMapping("/EmailWithAttachement")
    public String SendEmailWithAttachement(@RequestBody Msg msg) { 
        try {
            return  IEmailSenderService.SendEmailWithAttachement(   msg);
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }
}
