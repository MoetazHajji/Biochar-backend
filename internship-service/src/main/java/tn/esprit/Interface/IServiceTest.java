package tn.esprit.Interface;

import tn.esprit.Entity.Test;

import java.util.List;

public interface IServiceTest {


    void DeleteTest(Integer idTest);

    List<Test> getAllTest();

    Test findTest(Integer idTest);



    Test affecttesttofollowupsheet(int followUpSheetid, Test test);


    int getresult(int testid, List<String> internAnswers);

    Test addTest(Test test);
}
