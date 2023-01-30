package de.unibremen.akademie.kursverwaltung.application;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import de.unibremen.akademie.kursverwaltung.domain.PersonKurs;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY;
import static com.itextpdf.kernel.colors.ColorConstants.WHITE;
import static com.itextpdf.layout.properties.TextAlignment.RIGHT;
import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;


public class CreatePdf {

    // Zu erzeugende Dateien
    private final String SPEICHERPFAD = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/";
    private final String PERSONENLISTEPDF = SPEICHERPFAD + "Personenliste.pdf";
    private final String KURSELISTEPDF = SPEICHERPFAD + "Kurseliste.pdf";
    private final String ANWESENHEITSLISTEPDF = SPEICHERPFAD + "Anwesenheitsliste_";

    // Layout-Elemente
    private Color bgColor = LIGHT_GRAY;
    private final int fontSmall = 12;
    private final int fontBig = 16;
    private final int cellPadding = 5;
    private final float tblBreite = 520f;


    // aktuelles Datum
    private final SimpleDateFormat dateMitZeit = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private final SimpleDateFormat dateOhneZeit = new SimpleDateFormat("dd.MM.yyyy");
    private final String aktuellesDatum = dateMitZeit.format(new Date());

    // Format für die Dezimalstellen der Beträge
    private final NumberFormat geldBetrag = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    private final NumberFormat prozentWert = NumberFormat.getNumberInstance(Locale.GERMANY);

    // Seitenanzahl Berechnungsgrundlagen
    private int counterListenEintraege = 0;
    private int seiteAktuell = 1;
    private final int anzahlPersonenJeSeite = 10; //gerade Zahl
    private final int anzahlKurseJeSeite = 8; //gerade Zahl
    private final int anzahlTeilnehmerJeSeite = 20; //gerade Zahl
    private String trennZeichen = "/";
    private final String neueZeile = "\r\n";
    private final String tabulator = "\t";


    // PDF für die Liste aller Personen
    public void createPersonenListePdf() throws IOException {
        String headline = "Liste aller gespeicherten Personen (Stand: " + aktuellesDatum + ")";
        String metaSubject = "Personenliste";
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
            Table table = new Table(3).setWidth(tblBreite);
            // Tabellenheader holen
            tabellenHeader(table, headline, seitenGesamt);

            for (Object obj : kvModel.getPersonen().getPersonenListe()) {
                Person person = (Person) obj;
                counterListenEintraege++;
                if (counterListenEintraege <= anzahlPersonenJeSeite) {
                    if (counterListenEintraege % 2 != 0) {
                        bgColor = LIGHT_GRAY;
                    } else {
                        bgColor = WHITE;
                    }
                    table.addCell(new Cell(1, 3).add(new Paragraph(personToPDF(person)).setPadding(cellPadding)).setBackgroundColor(bgColor));
                } else {
                    seiteAktuell++;
                    // Tabellenheader holen
                    tabellenHeader(table, headline, seitenGesamt);
                    table.addCell(new Cell(1, 3).add(new Paragraph(personToPDF(person)).setPadding(cellPadding)).setBackgroundColor(LIGHT_GRAY));
                    counterListenEintraege = 1;
                }
            }
            personenListePdf.add(table);
        } else {
            personenListePdf.add(new Paragraph(headline).setFontSize(fontBig));
            personenListePdf.add(new Paragraph("neueZeileEs sind keine Personen in der Kursverwaltung angelegt!").setFontSize(fontBig));
        }

        seiteAktuell = 0; //reset
        personenListePdf.close();
    }

    // PDF für die Liste aller Kurse
    public void createKurseListePdf() throws IOException {
        String headline = "Liste aller gespeicherten Kurse (Stand: " + aktuellesDatum + ")";
        String metaSubject = "Kurseliste";
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
            Table table = new Table(3).setWidth(tblBreite);
            // Tabellenheader holen
            tabellenHeader(table, headline, seitenGesamt);

            for (Object obj : kvModel.getKurse().getKursListe()) {
                Kurs kurs = (Kurs) obj;
                counterListenEintraege++;
                if (counterListenEintraege <= anzahlKurseJeSeite) {
                    if (counterListenEintraege % 2 != 0) {
                        bgColor = LIGHT_GRAY;
                    } else {
                        bgColor = WHITE;
                    }
                    table.addCell(new Cell(1, 3).add(new Paragraph(kursToPDF(kurs)).setPadding(cellPadding)).setBackgroundColor(bgColor));
                } else {
                    seiteAktuell++;
                    // Tabellenheader holen
                    tabellenHeader(table, headline, seitenGesamt);
                    table.addCell(new Cell(1, 3).add(new Paragraph(kursToPDF(kurs)).setPadding(cellPadding)).setBackgroundColor(LIGHT_GRAY));
                    counterListenEintraege = 1;
                }
            }
            kurseListePdf.add(table);
        } else {
            kurseListePdf.add(new Paragraph(headline).setFontSize(fontBig));
            kurseListePdf.add(new Paragraph("neueZeileEs sind keine Kurse in der Kursverwaltung angelegt!").setFontSize(fontBig));
        }

        seiteAktuell = 0; //reset
        kurseListePdf.close();
    }

    // PDF für die Anwesenheitsliste
    public void createAnwesenheitslistePdf(String kursName, String datum) throws IOException {
        String headline = "Anwesenheitsliste für den Kurs " + kursName + ", " + datum;
        String metaSubject = "Anwesenheitsliste";
        String kursDatei = kursName.replace(" ", "_"); //Leerzeichen aus Dateinamen ersetzen

        // Anzahl der teilnehmenden Personen ermitteln
        int teilnehmendePersonen = 0;
        for (PersonKurs personKurs : kvModel.getPkListe().personKursList) {
            if (personKurs.getKurs().getName().equals(kursName) && personKurs.isTeilnehmer()) {
                teilnehmendePersonen++;
            }
        }
        int seitenGesamt = (teilnehmendePersonen + anzahlTeilnehmerJeSeite - 1) / anzahlTeilnehmerJeSeite;

        PdfDocument pdf = new PdfDocument(
                new PdfWriter(ANWESENHEITSLISTEPDF + kursDatei + "_" + datum + ".pdf",
                        new WriterProperties()
                                .addXmpMetadata()
                                .setPdfVersion(PdfVersion.PDF_1_6)));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        addMetaData(info, metaSubject); //Metadaten hinzufuegen
        Document anwesenheitslistePdf = new Document(pdf, PageSize.A4);

        if (seitenGesamt > 0) {
            Table table = new Table(3).setWidth(tblBreite);
            // Tabellenheader holen
            tabellenHeader(table, headline, seitenGesamt);

            for (PersonKurs personKurs : kvModel.getPkListe().personKursList) {
                if (personKurs.getKurs().getName().equals(kursName) && personKurs.isTeilnehmer()) {
                    Person person = personKurs.getPerson();
                    counterListenEintraege++;
                    if (counterListenEintraege <= anzahlTeilnehmerJeSeite) {
                        if (counterListenEintraege % 2 != 0) {
                            bgColor = LIGHT_GRAY;
                        } else {
                            bgColor = WHITE;
                        }
                        table.addCell(new Cell().add(new Paragraph(teilnehmerPersonToPDF(person)).setPadding(cellPadding)).setBackgroundColor(bgColor));
                        table.addCell(new Cell(1, 2).add(new Paragraph("").setPadding(cellPadding)).setBackgroundColor(bgColor));
                    } else {
                        seiteAktuell++;
                        // Tabellenheader holen
                        tabellenHeader(table, headline, seitenGesamt);
                        table.addCell(new Cell().add(new Paragraph(teilnehmerPersonToPDF(person)).setPadding(cellPadding)).setBackgroundColor(LIGHT_GRAY));
                        table.addCell(new Cell(1, 2).add(new Paragraph("").setPadding(cellPadding)).setBackgroundColor(LIGHT_GRAY));
                        counterListenEintraege = 1;
                    }
                }
            }
            anwesenheitslistePdf.add(table);
        } else {
            anwesenheitslistePdf.add(new Paragraph(headline).setFontSize(fontBig));
            anwesenheitslistePdf.add(new Paragraph("neueZeileEs gibt keine Teilnehmer für diesen Kurs!").setFontSize(fontBig));
        }

        seiteAktuell = 0; //reset
        anwesenheitslistePdf.close();
    }

    // Tabellenheader für die Listen erstellen
    public void tabellenHeader(Table table, String headline, int seitenGesamt) {
        String seitenAnzeige = seiteAktuell + trennZeichen + seitenGesamt;
        if (seitenGesamt == 1) {
            seitenAnzeige = neueZeile;
        }
        table.addCell(new Cell(1, 3).add(new Paragraph(headline).setPadding(cellPadding).setFontSize(fontBig)));
        table.addCell(new Cell(1, 3).add(new Paragraph(" ").setFontSize(fontBig))
                .add(new Paragraph(seitenAnzeige).setPadding(cellPadding).setFontSize(fontSmall))
                .setBorder(Border.NO_BORDER).setTextAlignment(RIGHT));
        if (headline.contains("Anwesenheitsliste")) {
            table.addCell(new Cell().setWidth(220f).add(new Paragraph("Teilnehmer:in").setPadding(cellPadding).setFontSize(fontBig)));
            table.addCell(new Cell(1, 2).add(new Paragraph("Unterschrift").setPadding(cellPadding).setFontSize(fontBig)));
        }
    }

    // Daten für die Liste aller Personen holen und formatieren
    public String personToPDF(Person person) {
        return person.getAnrede() + " " +
                person.getTitel() + " " +
                person.getVorname() + " " +
                person.getNachname() + neueZeile +
                person.getPlz() + " " +
                person.getOrt() + " " +
                person.getStrasse() + neueZeile +
                person.getEmail() + tabulator +
                person.getTelefon();
    }

    // Daten für die Liste aller Kurse holen und formatieren
    public String kursToPDF(Kurs kurs) {
        return kurs.getName() + "\tStatus: " + kurs.getStatus() + neueZeile +
                "Start: " + dateOhneZeit.format(kurs.getStartDatum()) + tabulator +
                "Ende: " + dateOhneZeit.format(kurs.getEndeDatum()) + tabulator +
                "Dauer: " + kurs.getAnzahlTage() + " Tage" + neueZeile +
                "Min. Teilnehmer: " + kurs.getMinTnZahl() + tabulator +
                "Max. Teilnehmer: " + kurs.getMaxTnZahl() + tabulator +
                "Freie Plätze: " + kurs.getFreiePlaetze() + neueZeile +
                "Gebühr Brutto: " + geldBetrag.format(kurs.getGebuehrBrutto()) + tabulator +
                "Gebühr Netto: " + geldBetrag.format(kurs.getGebuehrNetto()) + "  + " +
                "MwSt. (" + prozentWert.format(kurs.getMwstProzent()) + "%): " + geldBetrag.format(kurs.getMwstEuro());
    }

    // Personen-Daten für die Anwesenheitsliste holen und formatieren
    public String teilnehmerPersonToPDF(Person person) {
        return person.getAnrede() + " " +
                person.getTitel() + " " +
                person.getVorname() + " " +
                person.getNachname() + neueZeile;
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