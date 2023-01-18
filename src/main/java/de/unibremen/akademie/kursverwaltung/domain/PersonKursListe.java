package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class PersonKursListe {

    static public final PersonKursListe modelKP = new PersonKursListe();

    static public final ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();
//Mohamed
public static Boolean addPersonInKursAlsTeilnehmer(Person person, Kurs kurs) {

    for (PersonKurs personKurs : personKursList) {
        if (personKurs.getPerson().equals(person) && personKurs.getKurs().equals(kurs)) {
            System.out.println("Ich bin hier1" + personKursList.size());
            return false;
        }
    }
    PersonKurs personKurs = new PersonKurs(person, kurs, true);
    System.out.println("Ich bin hier2" + personKursList.size());
    personKursList.add(personKurs);
        return true;
    }


}
