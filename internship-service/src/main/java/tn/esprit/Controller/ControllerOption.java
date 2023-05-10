package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Detail;
import tn.esprit.Entity.Option;
import tn.esprit.Interface.IServiceOption;

import javax.ws.rs.QueryParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Option")
public class ControllerOption {
    IServiceOption iso;

    @PutMapping("/update")
    public Option updateOption(@RequestBody Option o,@QueryParam("questionid") int questionid) {
        System.out.printf(questionid + "");
        return iso.addOption(o,questionid);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOption(@PathVariable("id") Integer idOption)
    {
        iso.DeleteOption(idOption);
    }

    @GetMapping("/all")
    public List<Option> getAllOptions() {
        return iso.getAllOption();
    }

    @GetMapping("get/{id}")
    public Option getOption(@PathVariable("id") Integer idOption)
    {
        return iso.findOption(idOption);
    }

}
