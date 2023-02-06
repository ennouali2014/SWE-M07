package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KursListe {
    private ObservableList<Kurs> kursListe = FXCollections.observableArrayList();

    public void addKursZuListe(Kurs kurs) {
        kursListe.add(kurs);
    }

    //  should be list of courses rather than raw list type
    public ObservableList<Kurs> getKursListe() {
        return kursListe;
    }

    public Kurs getKursVonKursListe(int index) {
        return kursListe.get(index);
    }

    public Kurs addNewKurs(String name, int anzahlTage, int zyklus, Date startDatum, int minTnZahl, int maxTnZahl,
                           double gebuehrBrutto, double mwstProzent, String kursBeschreibung, String statusSTR) {
        Kurs kurs = new Kurs(name, anzahlTage, zyklus, startDatum, minTnZahl, maxTnZahl,
                gebuehrBrutto, mwstProzent, kursBeschreibung, statusSTR);
        addKursZuListe(kurs);

        return kurs;
    }

    private Date vonDatum;
    private Date bisDatum;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Date getVonDatum() {
        return vonDatum;
    }

    public void setVonDatum(Object value) {
        vonDatum = parseDate(value);
    }

    public Date getBisDatum() {
        return bisDatum;
    }

    public void setBisDatum(Object value) {
        Calendar calendar = Calendar.getInstance();
        bisDatum = parseDate(value);
        calendar.setTime(bisDatum);
        calendar.add(Calendar.DATE, 1);
        bisDatum = calendar.getTime();
    }

    private Date parseDate(Object value) {
        if (value == null || value.toString().isEmpty() || value.toString().isBlank()) {
            return null;
        }
        try {
            return dateFormat.parse(value.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void alertDatum(Date ab, Date bis) {
        if (ab.after(bis)) {
            Meldung.eingabeFehler("bisDatum muss großer als abDatum!!!");
            return;
        }
    }

    public boolean isBetween(Date date) {
        if (vonDatum != null && bisDatum != null) {
            return date.after(vonDatum) && date.before(bisDatum);
        } else if (vonDatum != null) {
            return date.after(vonDatum);
        } else if (bisDatum != null) {
            return date.before(bisDatum);
        }
        return true;
    }

}
