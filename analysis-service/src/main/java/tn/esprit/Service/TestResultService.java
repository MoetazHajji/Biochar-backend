package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Test;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.ITestResult;
import tn.esprit.Repository.TestRepository;
import tn.esprit.Repository.TestResultRepository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestResultService implements ITestResult {

    private final TestResultRepository testResultRepository;
    private final TestRepository testRepository;



    @Override
    public TestResult addOrUpdateTestResult(TestResult testResult) {
        return testResultRepository.save(testResult);
    }

    @Override
    public void removeSTestResult(int idTestResult) {
        testResultRepository.deleteById(idTestResult);
    }

    @Override
    public TestResult retriveTestResult(int idTestResult) {
        return testResultRepository.findById(idTestResult).orElse(null);
    }
    @Override
    public List<TestResult> retrieveAllTestResult() {

        List<TestResult> TestResults =new ArrayList<>();
        testResultRepository.findAll().forEach(TestResults::add);
        return TestResults;
    }
    @Override
    public List<TestResult> readTests(MultipartFile file) throws IOException {

          log.info("file :"+file.getSize());
          try {
              PdfDocument pdf = new PdfDocument(file.getInputStream());


               PdfTableExtractor extractor = new PdfTableExtractor(pdf);

              PdfTable[] tableLists = extractor.extractTable(0);
              log.info("file :"+(tableLists==null));
               if (tableLists != null && tableLists.length > 0) {
              log.info("it worked ");


                   for (int row = 1; row < tableLists[0].getRowCount(); row++) {


                         System.out.println( tableLists[0].getText(row, 0)+'|'+
                                 tableLists[0].getText(row, 1)+'|'+
                                 tableLists[0].getText(row, 2)+'|'+
                                 tableLists[0].getText(row, 3));
                           TestResult test = new TestResult(

                                   tableLists[0].getText(row, 0),
                                   tableLists[0].getText(row, 1),
                                   tableLists[0].getText(row, 2),
                                   tableLists[0].getText(row, 3)
                           );
                       System.out.println(test.toString());
                       test.toString();
                      try {

                          testResultRepository.save(test);
                      }catch (NullPointerException e)
                      {
                          System.out.println(e);
                      }

                   }

                }else{
              log.info("nope ");
               }
          }catch (UncheckedIOException err){
              log.info("error :",err);
          }



        return null;
    }

    @Override
    public TestResult asigntesTosmp(int idTestResult, int idTest) {
        Test d = testRepository.findById(idTest).orElse(null);
        TestResult e = testResultRepository.findById(idTestResult).orElse(null);
        e.setTest(d);

        return testResultRepository.save(e);
    }
    @Override
    public TestResult retriveTestResultBYAcounnt(int id) {
        return testResultRepository.getTestResultByTest_Sample_Account_Id(id);
    }
    }

