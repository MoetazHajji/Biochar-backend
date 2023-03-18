package tn.esprit.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/Page")
public class testPage {


    @GetMapping("/index")
    public String home_index(){
        return "index";
    }
    @GetMapping("/management-users")
    public String users_index(){
        return "user/index";
    }
    @GetMapping("/management-analysis")
    public String analysis_index(){
        return "analysis/index";
    }
    @GetMapping("/management-internship")
    public String management_index(){
        return "internship/index";
    }
    @GetMapping("/management-patient")
    public ResponseEntity<String>  patient_index(){
        return  ResponseEntity.ok("patient/index");
    }



    @GetMapping("/login")
    public String getModel (Model model) {
      //  model.addAttribute("thisatribute","cscscscsc");
        return  "login" ;}
}
