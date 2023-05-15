package tn.esprit.Controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.TestResult;
import tn.esprit.Interface.IEmail;
import tn.esprit.Interface.ISchuduld;
import tn.esprit.Interface.ITestResult;
import tn.esprit.Service.PdfGenerator;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("TestResult")
@RequiredArgsConstructor
public class TestResultController {
    private final ITestResult iTestResult;

    private final IEmail iEmail;
    private final ISchuduld iSchuduld;

    @PostMapping("/add")
    TestResult addtestResult(@RequestBody TestResult e){
        return iTestResult.addOrUpdateTestResult(e);
    }
    @DeleteMapping("/delete/{idTestResult}")
    void deleteResult(@PathVariable("idTestResult") int idTestResult){
        iTestResult.removeSTestResult(idTestResult);
    }
    @PutMapping("/update")
    TestResult updateTestResult(@RequestBody TestResult e){
        return iTestResult.addOrUpdateTestResult(e);
    }

    @GetMapping("/gette/{idTestResult}")
    TestResult affichtestResul(@PathVariable("idTestResult") int idTestResult){
        return iTestResult.retriveTestResult(idTestResult);
    }
    @GetMapping("/all")
    List<TestResult> getAllSample(){
        return iTestResult.retrieveAllTestResult();
    }
    @PostMapping("/readPDF")
    List<TestResult> readPDFtest(@RequestBody MultipartFile file){
        /*try {
            return iTestResult.readTests(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        return null;
    }

    @PutMapping("/asign/{idTestResult}/{idTest}")
    TestResult ModifierDep (@PathVariable ("idTestResult") Integer idTestResult, @PathVariable("idTest") Integer idTest){
        return iTestResult.asigntesTosmp(idTestResult,idTest);

    }
    @GetMapping("/get/{id}")
   List<TestResult>  affichBYAcounnt(@PathVariable("id") Long id){
        return iTestResult.retriveTestResultBYAcounnt(id);
    }
    @GetMapping("/getres")
    void affichBYResult() {
        try {
            iSchuduld.Reminder();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
   @GetMapping("/exporttopdf/{id}")
   public void generatePdfFile(HttpServletResponse response, @PathVariable("id") Long id) throws DocumentException, IOException, MessagingException {


       response.setContentType("application/pdf");

       DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH_MM_SS");

       String currentDateTime = dateFormat.format(new Date());

       String headerkey = "Content-Disposition";

       String headervalue = "attachment; filename=patients" + currentDateTime + ".pdf";

       response.setHeader(headerkey, headervalue);

       List<TestResult> testtt = iTestResult.retriveTestResultBYAcounnt(id);

       PdfGenerator generator = new PdfGenerator();

       generator.generateToEtudiant(testtt, response);
       iEmail.sendMessageWithAttachmentpatient("C:\\Users\\siwar\\Downloads\\".concat(headervalue.substring(21)));
   }
    @GetMapping("/getttt")
   long affich(){
        return iTestResult.countttt();
    }
    @GetMapping("/g")
    TestResult affichhhh(){
        return iTestResult.cou();
    }

}
