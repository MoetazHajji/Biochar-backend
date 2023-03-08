package tn.esprit.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tn.esprit.Interface.ITestResult;

import java.util.LinkedHashMap;
import java.util.Map;
@Controller
@RequiredArgsConstructor
public class GraphController {
    private final ITestResult iTestResult;
    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
        Map<String, Long> surveyMap = new LinkedHashMap<>();
        surveyMap.put("Babies", iTestResult.countttt());
       surveyMap.put("Young", iTestResult.counttt());
        surveyMap.put("overage", iTestResult.countt());
      //  surveyMap.put(".Net", 15);
        model.addAttribute("surveyMap", surveyMap);
        return "barGraph";
    }

    @GetMapping("/displayPieChart")
    public String pieChart(Model model) {
        model.addAttribute("Babies", iTestResult.countttt() );
        model.addAttribute("Young", iTestResult.counttt());
        model.addAttribute("overage", iTestResult.countt());
        return "pieChart";
    }
}
