package de.unibremen.akademie.kursverwaltung.application;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.layout.Document;
import de.unibremen.akademie.kursverwaltung.domain.Person;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

public class CreatePdf {

    // Zu erzeugende Dateien
    String PERSONENLISTEPDF = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/Personenliste.pdf";

    // Layout-Elemente
    public SolidLine line = new SolidLine(1f);
    public LineSeparator separator = new LineSeparator(line);

    public void createPersonenListePdf() throws IOException {
        PdfDocument pdf = new PdfDocument(
                new PdfWriter(PERSONENLISTEPDF,
                        new WriterProperties()
                                .addXmpMetadata()
                                .setPdfVersion(PdfVersion.PDF_1_6)));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        addMetaData(info, "Personenliste"); //Metadaten hinzufuegen
        Document personenListePdf = new Document(pdf, PageSize.A4);
        personenListePdf.add(new Paragraph("Liste aller gespeicherten Personen"));
        personenListePdf.add(separator);
        personenListePdf.add(new Paragraph(""));
        for (Object obj : kvModel.getPersonen().getPersonenListe()) {
            Person person = (Person) obj;
            personenListePdf.add(new Paragraph(personToPDF(person)));
            personenListePdf.add(separator);
        }
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

    private static void addMetaData(PdfDocumentInfo info, String subject) {
            info.setTitle("Kursverwaltung");
            info.setAuthor("SWE 2022/2023");
            info.setSubject(subject);
            info.setKeywords("M. Dbour | F. Ennouali | A. Förster | T. Haberland | Y. Hanna | A. Karaköse | R. Lukas | M. Pawel | O. Thiel | T. Tomy");
            info.setCreator("SWE-M07 - DevelopmentGroup");
    }
}