package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Detail;
import tn.esprit.Entity.FollowUpSheet;
import tn.esprit.Interface.IServiceFollowUpSheet;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/FollowUpSheet")
public class ControllerFollowUpSheet {
    IServiceFollowUpSheet isf;
    @PostMapping("/add")
    public FollowUpSheet addFollowUpSheet (@RequestBody FollowUpSheet f) {
        return isf.addFollowUpSheet(f);
    }
    @PutMapping("/update")
    public FollowUpSheet updateFollowUpSheet(@RequestBody FollowUpSheet f) {
        return isf.addFollowUpSheet(f);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteFollowUpSheet(@PathVariable("id") Integer idFollowUpSheet)
    {
        isf.DeleteFollowUpSheet(idFollowUpSheet);
    }
    @GetMapping("/all")
    public List<FollowUpSheet> getAllFollowUpSheets() {
        return isf.getAllFollowUpSheet();
    }

    @GetMapping("get/{id}")
    public FollowUpSheet getFollowUpSheet(@PathVariable("id") Integer idFollowUpSheet)
    {
        return isf.findFollowUpSheet(idFollowUpSheet);
    }










}
