package tn.esprit.Interface;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Cookie;
import tn.esprit.Entity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ISubjectService {
    void Delete_all();

    List<Subject> getAll();

    List<Subject> add_Data(MultipartFile file);

    void add_Cookies(HttpServletRequest request,int id_user);

    void add_Cookies_File(MultipartFile file,int id_user);

    Iterable<Cookie> getAllCookies();

    void clearCookies();

    Map<String,List<String>> Predictions();
}
