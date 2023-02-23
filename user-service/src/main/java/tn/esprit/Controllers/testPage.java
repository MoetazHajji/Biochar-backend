package tn.esprit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testPage {


    @GetMapping("/index")
    public String home_index(){
        return "index";
    }
    @GetMapping("/management-users/index")
    public String users_index(){
        return "user/index";
    }
    @GetMapping("/management-analysis/index")
    public String analysis_index(){
        return "analysis/index";
    }
    @GetMapping("/management-internship/index")
    public String management_index(){
        return "internship/index";
    }
    @GetMapping("/profile/index")
    public String profile_index(){
        return "profile/index";
    }




    @GetMapping("/login")
    public String getModel (Model model) {
      //  model.addAttribute("thisatribute","cscscscsc");
        return  "login" ;}
}
