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

    public Person addNewPerson(String anrede, String titel, String vorname, String nachname, String strasse, String plz, String ort, String email, String telefon) {
        Person person = new Person(anrede, titel, vorname, nachname, strasse, plz, ort, email, telefon);
        addPersonenZuListe(person);
        return person;
    }

}
