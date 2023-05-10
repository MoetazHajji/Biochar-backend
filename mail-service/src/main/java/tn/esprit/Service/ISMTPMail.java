package tn.esprit.Service;

import tn.esprit.Models.Msg;

import javax.mail.MessagingException;

public interface ISMTPMail {
    void connect ();
    void sending( Msg msg ) throws MessagingException ;
    void sendingSimple( Msg msg ) throws MessagingException ;
    void sendingWithViewHTML( Msg msg ) throws MessagingException ;
    void sendingWithDocument( Msg msg ) throws MessagingException ;
    void sendingWithDocuments( Msg msg ) throws MessagingException;
    void sendingWithStreamDocuments( Msg msg ) throws MessagingException;
}
