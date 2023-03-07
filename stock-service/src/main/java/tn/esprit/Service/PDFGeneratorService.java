package tn.esprit.Service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Command;
import tn.esprit.Repository.ICommandRepository;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PDFGeneratorService {
    ICommandRepository commandRepository;
    public void export (HttpServletResponse response,Long id) throws IOException, DocumentException {
        List<Command> commandList = commandRepository.findAll();
        Command command =commandRepository.findById(id).orElse(null);
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("List Of Students", fontTiltle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);

        // Creating a table of 3 columns
        PdfPTable table = new PdfPTable(3);

        // Setting width of table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 3, 3, 3 });
        table.setSpacingBefore(5);

        // Create Table Cells for table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding
        cell.setBackgroundColor(Color.GREEN);
        cell.setPadding(5);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        // Adding headings in the created table cell/ header
        // Adding Cell to table
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Notice", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total price", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Products", font));
        table.addCell(cell);
        // Iterating over the list of students
        for (Command commands : commandList) {
            // Adding command id
            table.addCell(String.valueOf(command.getId()));
            // Adding product notice
            table.addCell(command.getNotice());
            // Adding command total price
            table.addCell(String.valueOf(command.getTotal_price()));
            // Adding command products
            table.addCell(String.valueOf(command.getCommandLignes()));
        }
        // Adding the created table to document
        document.add(table);

        // Closing the document
        document.close();
        }
}
