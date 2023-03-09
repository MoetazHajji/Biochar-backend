package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.BarResponse;
import tn.esprit.Interface.ITestResult;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ITestResult iTestResult;
    public BarResponse bar(){


        BarResponse barResponse = new BarResponse();
        List<String> labels = new ArrayList<>();
        labels.add("Babies");
        labels.add("Young");
        labels.add("overage");
        List<Long> values = new ArrayList<>();
        values.add(iTestResult.countttt());
        values.add(iTestResult.counttt());
        values.add(iTestResult.countt());
        barResponse.setValues(values);
        barResponse.setLabels(labels);
        return  barResponse;


    }
}
