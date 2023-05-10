package tn.esprit.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Appointment;

import java.io.IOException;

public interface IFileService {
    String Edit_forgotPasswordPage (String username , String code   ) throws IOException;


    String Edit_ConfirmMailPage (String username ,  String link  ) throws IOException;
    String Edit_ReminderAppointementPage (String username , Appointment appointment  ) throws IOException;
    String convert_page_to_String_UTF_8 ( String srcPage) throws IOException;
    void write_file_UTF_8 ( String srcPage , String data) throws IOException;
     String Edit_sendMailSuggestionPage ( String username , Appointment appt, String link  ) throws IOException ;
     MultipartFile importFileToMultipartFile(String filePath) throws IOException;
}
