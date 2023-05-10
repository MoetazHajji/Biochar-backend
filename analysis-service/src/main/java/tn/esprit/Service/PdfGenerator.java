package tn.esprit.Service;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.Entity.TestResult;
import tn.esprit.Repository.TestResultRepository;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {
    @Autowired
    TestResultRepository testResultRepository;

    public void generateToEtudiant(List<TestResult> testResults, HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document

      Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter

        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it

        document.open();

        // Creating font


        // Setting font style and size

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        fontTiltle.setSize(15);
        fontTiltle.setColor(Color.DARK_GRAY);


        // Creating paragraph

        Paragraph paragraph1 = new Paragraph("Charles nicolle hospital department assessment of medical analyzes", fontTiltle);

        // Aligning the paragraph in the document

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in the document

        document.add(paragraph1);

        Image image = Image.getInstance("https://tse4.mm.bing.net/th?id=OIP.-T4Jd-muXqblDwmC27Pa6wAAAA&pid=Api&P=0");
        image.scaleToFit(50, 50);
        image.setBottom(12);


        document.add(image);

        // Creating a table of the 4 columns

        PdfPTable table = new PdfPTable(4);

        // Setting width of the table, its columns and spacing

        table.setWidthPercentage(100f);

        table.setWidths(new int[] {6,6,6,6});

        table.setSpacingBefore(8);

        // Create Table Cells for the table header

        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding of the table cell

        cell.setBackgroundColor(CMYKColor.red);

        cell.setPadding(4);

        // Creating font

        // Setting font style and size

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell or  header

        // Adding Cell to table

        cell.setPhrase(new Phrase("Test", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Result", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Unite", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Reference", font));

        table.addCell(cell);

        // Iterating the list of students

        for (TestResult testResultt: testResults) {


            table.addCell(testResultt.getTeest());


            table.addCell(testResultt.getResultat());


            table.addCell(testResultt.getUnite());


            table.addCell(testResultt.getUnite());


        }

        // Adding the created table to the document

        document.add(table);

        // Closing the document

        document.close();

    }
   /* public void generateToPatient(TestResult testR, HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document

      Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter

        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it

        document.open();

        // Creating font

        // Setting font style and size

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        fontTiltle.setSize(20);

        // Creating paragraph

        Paragraph paragraph1 = new Paragraph("La liste des Tests", fontTiltle);

        // Aligning the paragraph in the document

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in the document

        document.add(paragraph1);

        // Creating a table of the 4 columns

        PdfPTable table = new PdfPTable(5);

        // Setting width of the table, its columns and spacing

        table.setWidthPercentage(100f);

        table.setWidths(new int[] {3,3,3,3,3});

        table.setSpacingBefore(6);

        // Create Table Cells for the table header

        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding of the table cell

        cell.setBackgroundColor(CMYKColor.BLUE);

        cell.setPadding(5);

        // Creating font

        // Setting font style and size

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell or  header

        // Adding Cell to table

        cell.setPhrase(new Phrase("ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("PLAGE DE REFERENCE", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Test", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Unite", font));

        cell.setPhrase(new Phrase("Nom du patient", font));

        table.addCell(cell);

        // Iterating the list of students





        table.addCell(String.valueOf(testR.getIdTestResult()));


        table.addCell(testR.getPLAGE_DE_REFERENCE());


        table.addCell(testR.getTeest());


        table.addCell(testR.getUnite());

        table.addCell(testR.getTeest());



        // Adding the created table to the document

        document.add(table);

        // Closing the document

        document.close();

    }*/

}
