import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.UnitValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class PDFPrinter {
    public static void main(String[] args) throws IOException {
        printHelloWorld();
        printRickAstley();
        printTable();
    }

    public static void printHelloWorld() throws FileNotFoundException {
        String dest = "results/chapter01/hello_world.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World Again!"));
        document.close();
    }

    public static void printRickAstley() throws IOException {
        //Create a pdf document object with a given name and destination
        String destination = "results/chapter01/rickastley.pdf";
        PdfWriter pdfWriter = new PdfWriter(destination);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        //Create a pdf font
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);

        //Add paragraph
        document.add(new Paragraph("iText is:").setFont(font));

        //Create a list
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);

        //Add list item objects
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));

        //Add the list
        document.add(list);
        document.close();
    }

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
        String filename = "src/main/resources/united_states.csv";
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
