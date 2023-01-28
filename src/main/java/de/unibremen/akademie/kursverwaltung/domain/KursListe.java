package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KursListe {
    private ObservableList<Kurs> kursListe = FXCollections.observableArrayList();

    public void addKursZuListe(Kurs kurs) {
        kursListe.add(kurs);
    }

    public ObservableList getKursListe() {
        return kursListe;
    }

    public Kurs getKursVonKursListe(int index) {
        return kursListe.get(index);
    }
}
