package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.IEmail;
import tn.esprit.Interface.ISchuduld;
import tn.esprit.Repository.TestResultRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulrServicee implements ISchuduld {


    private final TestResultRepository testResultRepository;

    private final IEmail iEmail;

    @Override
   // @Scheduled(fixedRate = 60000)
    public void Reminder()  {
        List<TestResult> appointments = testResultRepository.findAll();

        for (TestResult testResult : appointments) {

            if (testResultRepository.countTestResul()==testResult) {


                iEmail.sendSimpleMessage("siwar.atiya@esprit.tn",
                        "Reminder",
                        "You have to make another test as soon as possible");


            }

        }

    }
}
