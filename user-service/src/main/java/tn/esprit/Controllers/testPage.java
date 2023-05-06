package tn.esprit.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/biochar")
public class testPage {


    @GetMapping("/index")
    public String home_index(){
        return "index";
    }
    @GetMapping("/management-users")
    public String users_index(){
        return "user/index";
    }



    @GetMapping("/internship/**")
    public String analysis_index(){
        return "internship-service";
    }
    @GetMapping("/stock-service/**")
    public String management_stock(){   return "stock-service"; }
    @GetMapping("/mail-service/**")
    public String management_mail(){   return "mail-service"; }

    @GetMapping("/login")
    public String getModel (Model model) {
      //  model.addAttribute("thisatribute","cscscscsc");
        return  "login" ;}
}
