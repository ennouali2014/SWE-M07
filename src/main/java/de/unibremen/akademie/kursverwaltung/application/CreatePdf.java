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

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.aktuellePerson;
import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

public class CreatePdf {

    // Zu erzeugende Dateien
    String DEST = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/Personenliste.pdf";
    File file = new File(DEST);


    // Layout-Elemente
    public SolidLine line = new SolidLine(1f);
    public LineSeparator separator = new LineSeparator(line);

    public void createPersonenListePdf() throws IOException {
        //file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);

        //Dokument für die Personenliste erstellen
        Document personenListePdf = new Document(pdf, PageSize.A4);
        personenListePdf.add(new Paragraph("Liste aller gespeicherten Personen"));
        personenListePdf.add(separator);
        personenListePdf.add(new Paragraph(""));
        for (Object obj : kvModel.getPersonen().getPersonenListe()) {
            Person person = (Person) obj;
            personenListePdf.add(new Paragraph(personToPDF(person)));
            personenListePdf.add(separator);
        }
        personenListePdf.add(separator);
        personenListePdf.close();
    }

    public String personToPDF (Person person) {
        return person.getAnrede() + " " +
                person.getTitel() + " " +
                person.getVorname() + " " +
                person.getNachname() + "\r" +
                person.getPlz() + " " +
                person.getOrt() + " " +
                person.getStrasse() + "\r" +
                person.getEmail() + " " +
                person.getTelefon();
    }
}