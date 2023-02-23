package tn.esprit.Services;

import tn.esprit.Models.Msg;

import javax.mail.MessagingException;

public interface IEmailSenderService {
     String  SendSimpleEmail(Msg msg);
     String  SendEmailWithAttachement(Msg msg) throws MessagingException;
}
