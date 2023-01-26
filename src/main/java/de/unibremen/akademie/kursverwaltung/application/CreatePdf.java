package de.unibremen.akademie.kursverwaltung.application;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.File;
import java.io.IOException;

public class CreatePdf {
    public static final String DEST = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/saved_pdf" + System.currentTimeMillis() + ".pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new CreatePdf().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // Add content to the document here
        // ...

        document.close();
    }
}
