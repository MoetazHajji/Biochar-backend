package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.FollowUpSheet;
import tn.esprit.Entity.Internship;
import tn.esprit.Entity.Test;
import tn.esprit.Interface.IServiceInternship;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Internship")
public class ControllerInternship {
    IServiceInternship ist;
    @PostMapping("/add/{request_id}")
    public Internship addInternship (@RequestBody Internship i,@PathVariable("request_id")int request_id) {
        return ist.addInternship(i,request_id);
    }
    @PutMapping("/update/{request_id}")
    public Internship updateInternship(@RequestBody Internship i,@PathVariable("request_id")int request_id) {
        return ist.addInternship(i,request_id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteInternship(@PathVariable("id") Integer idInternship)
    {
        ist.DeleteInternship(idInternship);
    }
    @GetMapping("/all")
    public List<Internship> getAllInternship() {
        return ist.getAllInternship();
    }
@GetMapping("getbyDate/{dateoftheday}")
public Test findtestbydate(@PathVariable("dateoftheday")  Date dateoftheday){
       return ist.findtestbydate(dateoftheday);
}
    @GetMapping("get/{id}")
    public Internship getInternship(@PathVariable("id") Integer idInternship)
    {
        return ist.findInternship(idInternship);
    }



}
