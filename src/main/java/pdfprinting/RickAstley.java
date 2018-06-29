package pdfprinting;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class RickAstley {
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
}
