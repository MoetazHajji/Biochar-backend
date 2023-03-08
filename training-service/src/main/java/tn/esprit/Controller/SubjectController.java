package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Cookie;
import tn.esprit.Entity.Subject;
import tn.esprit.Interface.ISubjectService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("subjects")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SubjectController {
    final ISubjectService subjectService;

    @DeleteMapping("deleteAll")
    public void delete_All()
    {
        subjectService.Delete_all();
    }

    @GetMapping("getAll")
    public List<Subject> getAll()
    {
        return subjectService.getAll();
    }

    @PostMapping("add_subjects")
    public List<Subject> add_Data(MultipartFile file)
    {
        return subjectService.add_Data(file);
    }

    @PostMapping("/all-cookies/{id_user}")
    public void readAllCookies(HttpServletRequest request,@PathVariable("id_user") int id_user) {


            javax.servlet.http.Cookie cookie1 = new javax.servlet.http.Cookie("1694083347","AHWqTUmjsuqbGDNRM3trnCfdm");
            cookie1.setDomain("en.wikipedia.org");
            cookie1.setPath("/wiki/Immunology");

        javax.servlet.http.Cookie[] cookies = new javax.servlet.http.Cookie[]{cookie1};

        subjectService.add_Cookies(cookies,id_user);
    }

    @PostMapping("add-cookies/{id_user}")
    public void add_Cookies_File(MultipartFile file,@PathVariable("id_user") int id_user)
    {
        subjectService.add_Cookies_File(file,id_user);
    }

    @GetMapping("get-cookies")
    public Iterable<Cookie> getAllCookies()
    {
        return subjectService.getAllCookies();
    }

    @DeleteMapping("clear-cookies")
    public void clearCookies()
    {
        subjectService.clearCookies();
    }


    @GetMapping("getSuggesions")
    public Map<String,List<String>> Predictions()
    {
        return subjectService.Predictions();
    }
}
