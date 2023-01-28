package de.unibremen.akademie.kursverwaltung.application;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.properties.AreaBreakType;
import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreatePdf {

    // Zu erzeugende Dateien
    String PERSONENLISTEPDF = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/Personenliste.pdf";
    String KURSELISTEPDF = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/Kurseliste.pdf";
    String ANWESENHEITSLISTEPDF = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/Anwesenheitsliste.pdf";

    // Layout-Elemente
    public SolidLine linie = new SolidLine(1f);
    public LineSeparator trennLinie = new LineSeparator(linie);

    // aktuelles Datum
    SimpleDateFormat dateMitZeit = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    SimpleDateFormat dateOhneZeit = new SimpleDateFormat("dd.MM.yyyy");
    String aktuellesDatum = dateMitZeit.format(new Date());

    // Format für die Dezimalstellen der Beträge
    NumberFormat geldBetrag = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    NumberFormat prozentWert = NumberFormat.getNumberInstance(Locale.GERMANY);

    // Seitenanzahl Berechnungsgrundlagen
    int counterListenEintraege = 0;
    int seiteAktuell = 1;
    int anzahlPersonenJeSeite = 10;
    int anzahlKurseJeSeite = 8;


    // PDF für die Liste aller Personen
    public void createPersonenListePdf() throws IOException {
        String headline = "Liste aller gespeicherten Personen (Stand: " + aktuellesDatum +")";
        String metaSubject = "Personenliste";
        String tabsAbstand = "\t\t\t";
        int seitenGesamt = (kvModel.getPersonen().getPersonenListe().size() + anzahlPersonenJeSeite - 1) / anzahlPersonenJeSeite;

        PdfDocument pdf = new PdfDocument(
                new PdfWriter(PERSONENLISTEPDF,
                        new WriterProperties()
                                .addXmpMetadata()
                                .setPdfVersion(PdfVersion.PDF_1_6)));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        addMetaData(info, metaSubject); //Metadaten hinzufuegen
        Document personenListePdf = new Document(pdf, PageSize.A4);

        if (seitenGesamt > 0) {
            personenListePdf.add(new Paragraph(headline + tabsAbstand + seiteAktuell + "/" + seitenGesamt).setFontSize(16));
            personenListePdf.add(trennLinie);
            personenListePdf.add(new Paragraph("\n\n"));
            for (Object obj : kvModel.getPersonen().getPersonenListe()) {
                Person person = (Person) obj;
                counterListenEintraege++;
                if (counterListenEintraege <= anzahlPersonenJeSeite) {
                    personenListePdf.add(new Paragraph(personToPDF(person)));
                    personenListePdf.add(trennLinie);
                } else {
                    personenListePdf.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    seiteAktuell++;
                    personenListePdf.add(new Paragraph(headline + tabsAbstand + seiteAktuell + "/" + seitenGesamt).setFontSize(16));
                    personenListePdf.add(trennLinie);
                    personenListePdf.add(new Paragraph("\n\n"));
                    personenListePdf.add(new Paragraph(personToPDF(person)));
                    personenListePdf.add(trennLinie);
                    counterListenEintraege = 1;
                }
            }
        } else {
            personenListePdf.add(new Paragraph(headline).setFontSize(18));
            personenListePdf.add(new Paragraph("\nEs sind keine Personen in der Kursverwaltung angelegt!").setFontSize(18));
        }

        seiteAktuell = 0; //reset
        personenListePdf.close();
    }

    // PDF für die Liste aller Kurse
    public void createKurseListePdf() throws IOException {
        String headline = "Liste aller gespeicherten Kurse (Stand: " + aktuellesDatum +")";
        String metaSubject = "Kurseliste";
        String tabsAbstand = "\t\t\t\t";
        int seitenGesamt = (kvModel.getKurse().getKursListe().size() + anzahlKurseJeSeite - 1) / anzahlKurseJeSeite;

        PdfDocument pdf = new PdfDocument(
                new PdfWriter(KURSELISTEPDF,
                        new WriterProperties()
                                .addXmpMetadata()
                                .setPdfVersion(PdfVersion.PDF_1_6)));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        addMetaData(info, metaSubject); //Metadaten hinzufuegen
        Document kurseListePdf = new Document(pdf, PageSize.A4);

        if (seitenGesamt > 0) {
            kurseListePdf.add(new Paragraph(headline + tabsAbstand + seiteAktuell + "/" + seitenGesamt).setFontSize(16));
            kurseListePdf.add(trennLinie);
            kurseListePdf.add(new Paragraph("\n\n"));
            for (Object obj : kvModel.getKurse().getKursListe()) {
                Kurs kurs = (Kurs) obj;
                counterListenEintraege++;
                if (counterListenEintraege <= anzahlKurseJeSeite) {
                    kurseListePdf.add(new Paragraph(kursToPDF(kurs)));
                    kurseListePdf.add(trennLinie);
                } else {
                    kurseListePdf.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    seiteAktuell++;
                    kurseListePdf.add(new Paragraph(headline + tabsAbstand + seiteAktuell + "/" + seitenGesamt).setFontSize(16));
                    kurseListePdf.add(trennLinie);
                    kurseListePdf.add(new Paragraph("\n\n"));
                    kurseListePdf.add(new Paragraph(kursToPDF(kurs)));
                    kurseListePdf.add(trennLinie);
                    counterListenEintraege = 1;
                }
            }
        } else {
            kurseListePdf.add(new Paragraph(headline).setFontSize(18));
            kurseListePdf.add(new Paragraph("\nEs sind keine Kurse in der Kursverwaltung angelegt!").setFontSize(18));
        }

        seiteAktuell = 0; //reset
        kurseListePdf.close();
    }

    // Daten für die Liste aller Personen holen und formatieren
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

    // Daten für die Liste aller Kurse holen und formatieren
    public String kursToPDF (Kurs kurs) {
        return kurs.getName() + "\tStatus: " + kurs.getStatus() + "\n" +
                "Start: " + dateOhneZeit.format(kurs.getStartDatum()) + "\t" +
                "Ende: " + dateOhneZeit.format(kurs.getEndeDatum()) + "\t" +
                "Dauer: " + kurs.getAnzahlTage() + " Tage\r" +
                "Min. Teilnehmer: " + kurs.getMinTnZahl() + "\t" +
                "Max. Teilnehmer: " + kurs.getMaxTnZahl() + "\t" +
                "Freie Plätze: " + kurs.getFreiePlaetze() + "\r" +
                "Gebühr Brutto: " + geldBetrag.format(kurs.getGebuehrBrutto()) + "  \t" +
                "Gebühr Netto: " + geldBetrag.format(kurs.getGebuehrNetto()) + "  + " +
                "MwSt. (" + prozentWert.format(kurs.getMwstProzent()) + "%): " + geldBetrag.format(kurs.getMwstEuro());
    }

    // Metadaten die in den PDF-Infos gespeichert werden
    private static void addMetaData(PdfDocumentInfo info, String subject) {
            info.setTitle("Kursverwaltung");
            info.setAuthor("SWE-M07 - DevelopmentGroup");
            info.setSubject(subject);
            info.setKeywords("M. Dbour | F. Ennouali | A. Förster | T. Haberland | Y. Hanna | A. Karaköse | R. Lukas | M. Pawel | O. Thiel | T. Tomy");
            info.setCreator("SWE 2022/2023 - Kursverwaltung v1.0");
    }
}