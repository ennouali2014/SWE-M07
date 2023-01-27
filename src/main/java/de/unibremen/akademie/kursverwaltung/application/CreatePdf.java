package de.unibremen.akademie.kursverwaltung.application;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
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

    public void createPdf(String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        // Layout-Elemente
        SolidLine line = new SolidLine(1f);
        LineSeparator separator = new LineSeparator(line);

        //Dokument für die Personenliste erstellen
        Document personenListePdf = new Document(pdf, PageSize.A4);
        personenListePdf.add(new Paragraph("Liste aller gespeicherten Personen"));
        personenListePdf.add(separator);
        personenListePdf.add(new Paragraph(""));
        for (Object obj : kvModel.getPersonen().getPersonenListe()) {
            Person person = (Person) obj;
            personenListePdf.add(new Paragraph(person.personToPDF()));
            personenListePdf.add(separator);
        }
        personenListePdf.add(separator);
        personenListePdf.close();

    }
}