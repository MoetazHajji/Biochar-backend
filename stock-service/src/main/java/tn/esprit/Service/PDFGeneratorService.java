package tn.esprit.Service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.Product;
import tn.esprit.Repository.ICommandRepository;
import tn.esprit.Repository.IProductRepository;

import javax.swing.border.Border;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PDFGeneratorService {
    ICommandRepository commandRepository;
    IProductRepository productRepository;

    public byte[] generatePdfForCommand(Long commandId) throws DocumentException {
        // Fetch the products for the given command ID
        Command command = commandRepository.findById(commandId).orElse(null);
        Set<Product> products = productRepository.findProductsByCommandId(commandId);

        // Create a new PDF document
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        // Open the document
        document.open();

        Font labelFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        Font valueFont = new Font(Font.TIMES_ROMAN, 12);

        // Add a title to the document
        Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD);
        Paragraph title = new Paragraph("Command #" + command.getId() + " Summary", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add the customer's name and email to the document
        Font customerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Paragraph customer = new Paragraph("Customer: Charles Nicolle (charles.nicole@gmail.com)", customerFont);
        customer.setSpacingBefore(20);
        document.add(customer);

        // Add a table of products to the document
        PdfPTable table = new PdfPTable(new float[] { 1, 3, 2 });
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        PdfPCell cell1 = new PdfPCell(new Paragraph("Name", labelFont));
        cell1.setBackgroundColor(Color.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Description", labelFont));
        cell2.setBackgroundColor(Color.LIGHT_GRAY);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Paragraph("Price", labelFont));
        cell3.setBackgroundColor(Color.LIGHT_GRAY);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);


        // Add the product information to the document
        for (Product product : products) {
            table.addCell(new PdfPCell(new Paragraph(product.getName_product(), valueFont)));
            table.addCell(new PdfPCell(new Paragraph(product.getDescription(), valueFont)));
            table.addCell(new PdfPCell(new Paragraph(product.getPrice().toString() +"dt", valueFont)));
            document.add(table);
        }

        // Add the total price to the document
        Double totalPrice = command.getTotal_price();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("Total Price: " + totalPrice.toString() + "dt"));
        document.add(paragraph);


        // Close the document
        document.close();

        // Return the PDF document as a byte array
        return outputStream.toByteArray();
    }
}
