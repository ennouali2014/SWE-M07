package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
