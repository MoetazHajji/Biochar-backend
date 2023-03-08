package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Internship;
import tn.esprit.Entity.InternshipRequest;
import tn.esprit.Entity.Subject;
import tn.esprit.Interface.IServiceSubject;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Subject")
public class ControllerSubject {
    IServiceSubject iss;

   // @PostMapping("/add")
   // public Subject addSubject (@RequestBody Subject s) {
       // return iss.addSubject(s);
  //  }
    @PutMapping("/update")
    public Subject updateSubject(@RequestBody Subject s) {
        return iss.addSubject(s);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteSubject(@PathVariable("id") Integer idSubject)
    {
        iss.DeleteSubject(idSubject);
    }
    @GetMapping("/all")
    public List<Subject> getAllSubject() {
        return iss.getAllSubject();
    }

    @GetMapping("get/{id}")
    public Subject getSubject(@PathVariable("id") Integer idSubject)
    {
        return iss.findSubject(idSubject);
    }

}
