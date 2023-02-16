package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Detail;
import tn.esprit.Interface.IServiceDetail;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Detail")
public class ControllerDetail {
IServiceDetail isd;
    @PostMapping("/add")
    public Detail addDetail (@RequestBody Detail d) {
        return isd.addDetail(d);
    }
    @PutMapping("/update")
    public Detail updateDetail(@RequestBody Detail d) {
        return isd.addDetail(d);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDetail(@PathVariable("id") Integer idDetail)
    {
        isd.DeleteDetail(idDetail);
    }

    @GetMapping("/all")
    public List<Detail> getAllDetails() {
        return isd.getAllDetail();
    }

    @GetMapping("get/{id}")
    public Detail getDetail(@PathVariable("id") Integer idDetail)
    {
        return isd.findDetail(idDetail);
    }


}
