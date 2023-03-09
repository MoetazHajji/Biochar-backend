package tn.esprit.Services;

import tn.esprit.Entitys.Appointment;

import java.io.IOException;

public interface IFileService {
    String Edit_forgotPasswordPage (String username , String code , String link , String pageHomeLink ) throws IOException;
    String Edit_ConfirmMailPage (String username ,  String link , String pageHomeLink ) throws IOException;
    String Edit_ReminderAppointementPage (String username , Appointment appointment , String pageHomeLink ) throws IOException;
    String convert_page_to_String_UTF_8 ( String srcPage) throws IOException;
    void write_file_UTF_8 ( String srcPage , String data) throws IOException;
     String Edit_sendMailSuggestionPage ( String username , Appointment appt, String link , String pageHomeLink ) throws IOException ;

    }
