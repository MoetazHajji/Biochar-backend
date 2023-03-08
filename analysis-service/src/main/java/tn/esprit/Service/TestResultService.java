package tn.esprit.Service;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
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
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   /* @Override
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
                                   tableLists[0].getText(row,1),
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
    }*/
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
                           tableLists[0].getText(row,1),
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
    public List<TestResult> retriveTestResultBYAcounnt(Long id) {
        return testResultRepository.getTestResultByTest_Sample_Account_Id(id);

    }
    @Override
    public TestResult retriveTestResult(String resultat) {
        return testResultRepository.getTestResultByResultat(resultat);

    }
    @Override
    public long countttt() {
        return testResultRepository.countTestResultByDa();

    }
    @Override
    public long counttt() {
        return testResultRepository.countTestResultByDat();

    }
    @Override
    public long countt() {
        return testResultRepository.countTestResultByDate();

    }
    @Override
    public TestResult cou() {
        return testResultRepository.countTestResul();

    }

    @Override
    public Map<Integer,String[]> divide_table(String table) {
        String[] lines = table.split("\n");
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Map<Integer,String[]> map = new HashMap<>();
        for(int i =1;i<lines.length;i++)
        {

            String[] words = lines[i].split("\\s+");
            if(words.length >1)
            map.put(i,words);
        }

        for(String[] line : map.values())
        {

            TestResult testResult = new TestResult();
            testResult.setDate(date);
            testResult.setTeest(line[0]);
            testResult.setResultat(line[1]);
            testResult.setUnite(line[2]);
            testResult.setPLAGE_DE_REFERENCE(line[3]);
            testResultRepository.save(testResult);
        }
        return map;
    }
}

