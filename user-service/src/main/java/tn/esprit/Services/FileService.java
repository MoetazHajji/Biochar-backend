package tn.esprit.Services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Appointment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Libs.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service("File")
public class FileService implements IFileService {
    public static String link_forgotPassword_HTML = "";
    public static String link_ConfirmMail_HTML = "";
    public static String link_ReminderAppointement_HTML = "";
    public static String link_sendMailSuggestionForPatient = "";
    public static String  defaultUserPhoto = "";
    public static String pageHomeLink = "";

    public static String pathLogin = "";
    @Override
    public String Edit_sendMailSuggestionPage ( String username , Appointment appt, String link ) throws IOException {
        //https://zetcode.com/java/jsoup/
        File input = ResourceUtils.getFile(link_sendMailSuggestionForPatient);
        Document doc = Jsoup.parse(input,  "UTF-8");
        Element herder_username = doc.getElementById("suggestion-appointement-username-h2");
        Element dateElement = doc.getElementById("suggestion-appointement-date-span");
        Element timeElement = doc.getElementById("suggestion-appointement-start-time-span");
        Element sr_link = doc.getElementById("suggestion-link-click-confirm-a");
        Element paragrahelink = doc.getElementById("suggestion-link-page-home-a");
        herder_username.text( "Dear "+ username+",");
        dateElement.text(String.valueOf(appt.getAppointmentDate()));
        timeElement.text(String.valueOf(appt.getAppointmentStartTime()));
        sr_link.attr("href", link );
        paragrahelink.attr("href",FileService.pageHomeLink);
        return doc.html();
    }


    @Override
//"http://baeldung.com"
    public String Edit_ReminderAppointementPage (String username , Appointment appointment ) throws IOException {
        //https://zetcode.com/java/jsoup/
        File input = ResourceUtils.getFile(link_ReminderAppointement_HTML);
        Document doc = Jsoup.parse(input,  "UTF-8");
        Element herder_username = doc.getElementById("reminder-appointement-username-h2");
        Element dateStrong = doc.getElementById("reminder-appointement-date-strong");
        Element startTimeStrong  = doc.getElementById("reminder-appointement-start-time-strong");
        Element dateSpan = doc.getElementById("reminder-appointement-date-span" );
        Element startTimeSpan = doc.getElementById("reminder-appointement-start-time-span");
        Element endTimeSpan = doc.getElementById("reminder-appointement-end-time-span");
        Element reason = doc.getElementById("reminder-appointement-reason-span");
        Element firstVisitSpan = doc.getElementById("reminder-appointement-first-visit-span");
        Element statusSpan = doc.getElementById("reminder-appointement-appointment-status-span");
        Element createAt = doc.getElementById("reminder-appointement-create-at-span");
        Element paragrahelink = doc.getElementById("reminder-appointement-link-page-home-a");
        herder_username.text( "Dear "+ username+",");
        dateStrong.text(String.valueOf(appointment.getAppointmentDate()));
        startTimeStrong.text(String.valueOf(appointment.getAppointmentStartTime()));
        dateSpan.text(String.valueOf(appointment.getAppointmentDate()));
        startTimeSpan.text(String.valueOf(appointment.getAppointmentStartTime()));
        endTimeSpan.text(String.valueOf(appointment.getAppointmentEndTime()));
        reason.text(appointment.getReason());
        firstVisitSpan.text((appointment.isFirstVisit()? "Yes":"No"));
        statusSpan.text(String.valueOf(appointment.getAppointmentStatus()));
        createAt.text(String.valueOf(appointment.getCreatedAt()));
        paragrahelink.attr("href",FileService.pageHomeLink);
        return doc.html();
    }
    @Override
//"http://baeldung.com"
    public String Edit_ConfirmMailPage (String username ,  String link  ) throws IOException {
        //https://zetcode.com/java/jsoup/
        File input = ResourceUtils.getFile(link_ConfirmMail_HTML);
        Document doc = Jsoup.parse(input,  "UTF-8");
        Element herder_username = doc.getElementById("verify-mail-link-username-h1");
        Element sr_link = doc.getElementById("verify-mail-link-active-account-a");
        Element paragrahelink = doc.getElementById("verify-mail-link-page-home-a");
        herder_username.text( "Dear "+ username+",");
        sr_link.attr("href", link );
        paragrahelink.attr("href",FileService.pageHomeLink);
        return doc.html();
    }
   @Override
//"http://baeldung.com"
   public String Edit_forgotPasswordPage (String username , String code   ) throws IOException {
     //https://zetcode.com/java/jsoup/
         File input = ResourceUtils.getFile(link_forgotPassword_HTML);
         Document doc = Jsoup.parse(input,  "UTF-8");
         Element herder_username = doc.getElementById("forgot-password-code-username-h1");
         Element paragrahecode = doc.getElementById("forgot-password-code-verification-p");
         Element sr_link = doc.getElementById("forgot-password-link-verification-a");
         Element paragrahelink = doc.getElementById("forgot-password-link-page-home-a");
         herder_username.text( "Hi "+ username+",");
         paragrahecode.text(code);
         sr_link.attr("href", pageHomeLink+pathLogin+"/"+code );
         paragrahelink.attr("href",FileService.pageHomeLink);
         return doc.html();
   }
    @Override
   public String convert_page_to_String_UTF_8 ( String srcPage) throws IOException {
         File input = ResourceUtils.getFile(srcPage);
         return new String(Files.readAllBytes(input.toPath()), StandardCharsets.UTF_8);
   }
    @Override
    public void write_file_UTF_8 ( String srcPage , String data) throws IOException {
        File input = ResourceUtils.getFile(srcPage);
        PrintWriter writer = new PrintWriter(input,"UTF-8");
        writer.write(data) ;
        writer.flush();
        writer.close();
    }
    @Override
    public MultipartFile importFileToMultipartFile(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        if (file.exists()) {
            Path path = Paths.get(filePath);
            String name = file.getName();
            String originalFileName = name.substring(0, name.lastIndexOf('.'));
            String contentType = Files.probeContentType(path);
            byte[] content = Files.readAllBytes(path);
            return new MockMultipartFile(originalFileName, name, contentType, content);
        }
        return null;
    }
}
