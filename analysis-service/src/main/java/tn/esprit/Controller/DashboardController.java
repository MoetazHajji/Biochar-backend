package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.Entity.BarResponse;
import tn.esprit.Service.DashboardService;

@RestController
@AllArgsConstructor
public class DashboardController {
    private DashboardService dashboardService;


    @GetMapping("/nbpatient")
    public BarResponse barTest() {
        return  dashboardService.bar();
    }
}
