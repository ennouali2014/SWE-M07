package de.unibremen.akademie.kursverwaltung.application;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

public class CreatePdf {

    static Person person = new Person();
    public void createPdf(String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // Layout-Elemente
        SolidLine line = new SolidLine(1f);
        LineSeparator separator = new LineSeparator(line);

        document.add(new Paragraph("Liste aller gespeicherten Personen"));
        document.add(separator);
        document.add(new Paragraph(""));

        for (Object obj : kvModel.getPersonen().getPersonenListe()) {
            Person person = (Person) obj;
            document.add(new Paragraph(person.personToPDF()));
            document.add(separator);
        }

        document.close();
    }
}