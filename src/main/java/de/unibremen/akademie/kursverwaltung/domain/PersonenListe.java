package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonenListe {
    /**
     * ObservableList hat eine Objekt von Person
     * diese liste ist initialized mit ein instance of "FXCollections.observableArrayList()"
     * es ist erst mal leer gesetz,
     */
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

    /**
     * @param anrede
     * @param titel
     * @param vorname
     * @param nachname
     * @param strasse
     * @param plz
     * @param ort
     * @param email
     * @param telefon
     * @return wir verwenden diese Methode, um eine neue Person in die Observable Liste der Personen aufzusetzen.
     */

    public Person addNewPerson(String anrede, String titel, String vorname, String nachname, String strasse, String plz, String ort, String email, String telefon) {
        Person person = new Person(anrede, titel, vorname, nachname, strasse, plz, ort, email, telefon);
        addPersonenZuListe(person);
        return person;
    }
}
