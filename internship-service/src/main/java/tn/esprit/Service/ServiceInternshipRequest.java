package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.And;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.InternshipRequest;
import tn.esprit.Interface.IServiceInternshipRequest;
import tn.esprit.Repository.RepoInternship;
import tn.esprit.Repository.RepoInternshipRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
//pdf to string
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


@AllArgsConstructor
@Service
@Slf4j
public class ServiceInternshipRequest implements IServiceInternshipRequest {
    RepoInternshipRequest rir;
    RepoInternship ri;

    ResourceLoader resourceLoader;

    @Override
    public InternshipRequest addInternshipRequest(InternshipRequest ir) {
        return rir.save(ir);
    }

    @Override
    public void DeleteInternshipRequest(Integer idInternshipRequest) {
        rir.deleteById(idInternshipRequest);

    }

    @Override
    public List<InternshipRequest> getAllInternshipRequest() {
        List<InternshipRequest> InternshipRequestList = new ArrayList<>();
        rir.findAll().forEach(InternshipRequestList::add);

        return InternshipRequestList;
        //return rir.findAll();
    }

    @Override
    public InternshipRequest findInternshipRequest(Integer idInternshipRequest) {
        return rir.findById(idInternshipRequest).orElse(null);
    }

    @Override
    public InternshipRequest add_request_with_cv(InternshipRequest ir, MultipartFile cv) {
        try {
            String data = extractTextFromPDF(cv);
            if ((!validate_data(data))||(ir.getEnd_date().before(ir.getStart_date())))
               return null;

            ir.setCV("src\\main\\resources\\Files\\"+ir.getNom()+"_"+ir.getPrenom()+".pdf");
            InputStream inputStream = cv.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            File destinationFile = new File(ir.getCV());
            FileUtils.writeByteArrayToFile(destinationFile, bytes);
            return rir.save(ir);
        } catch (IOException ioe) {
            log.error("IO Problem : " + ioe.getMessage());
            return null;
        }
    }


/*    @Override
    public InternshipRequest add_request_with_cv(InternshipRequest ir) {
            return rir.save(ir);
    }*/


    private String extractTextFromPDF(MultipartFile cv) throws IOException {
        InputStream inputStream;
        inputStream = cv.getInputStream();
        byte[] pdfBytes = FileCopyUtils.copyToByteArray(inputStream);
        PDDocument document = PDDocument.load(pdfBytes);
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
    }

    private boolean validate_data(String data){
        if(!isFormal(data))
            return false;
        if (!isLongEnough(data,200,2000))
            return false;
        try {
            if (!check_bad_words(data))
                return false;
        }catch (IOException ioException)
        {
            log.error(ioException.getMessage());
            log.error("fichier text a un problem!");
        }
        return true;
    }
    private Boolean isFormal(String text)
    {
        int informalCount = 0;
        String[] tokens = text.split("\\s+");
        for (String token : tokens) {
            if (token.matches("(?i:i|me|my|you|your|he|him|his|she|her|hers|it|its|we|us|our|they|them|their|this|that|these|those)")) {
                informalCount++;
            }
            if (token.matches("(?i:gonna|wanna|gotta|shoulda|coulda|woulda|ain't|dunno|cuz|lol|omg|wtf)")) {
                informalCount++;
            }
        }
        double informalRatio = (double) informalCount / tokens.length;
        if (informalRatio > 0.2) {

            return false;
        }

        return true;
    }
    private boolean isLongEnough(String text,double minimumwordsPerPage,double maximumwordsperpage) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        if ((wordCount<=maximumwordsperpage)&(wordCount>=minimumwordsPerPage)) {

            return true;

        }

        return false;
    }
    private Boolean check_bad_words(String data) throws IOException {
        Resource resource = new FileSystemResource("C:/Users/HP/Desktop/pidev2023/Biochar-backend/internship-service/src/main/resources/Files/Bad.txt");
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
            stringBuilder.append("/");
        }
        String[] badWords = stringBuilder.toString().split("/");

        for(String bad : badWords)
        {

            if(data.contains(" "+bad+" ")) {

                return false;
            }
        }

        return true;

    }



}
