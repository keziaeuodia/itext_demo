package pdfprinting;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class States {
    public static void printTable() throws IOException {
        //Create the PDF documents, define the page size and rotate it
        String destination = "results/chapter01/us-states.pdf";
        PdfWriter writer = new PdfWriter(destination);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());

        //Define the page format
        document.setMargins(20, 20, 20, 20);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        //Define a table
        Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
        table.setWidth(UnitValue.createPercentValue(100));

        //Read the file to print the table from
        String filename = "src/Main/resources/united_states.csv";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();

        //Process the table's headers
        process(table, line, bold, true);

        //Process the table's cells
        while ((line = br.readLine()) != null) {
            process(table, line, font, false);
        }

        //Add the table and close the file
        br.close();
        document.add(table);
        document.close();

    }

    public static void process(Table table, String line, PdfFont font, boolean isHeader) {
        //Separated the line into tokens separated by a given delimiter symbol
        StringTokenizer tokenizer = new StringTokenizer(line, ";");

        //Adds the headers and cells to the table
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }
}
