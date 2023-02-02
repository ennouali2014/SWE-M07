package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

}
