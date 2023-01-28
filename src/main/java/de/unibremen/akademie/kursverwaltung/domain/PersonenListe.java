package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonenListe {
    private ObservableList<Person> personenliste = FXCollections.observableArrayList();

    public void addPersonenZuListe(Person person) {
        personenliste.add(person);
    }

    public ObservableList getPersonenListe() {
        return personenliste;
    }

    public Person getPersonVonPersonenListe(int index) {
        return personenliste.get(index);
    }
}
