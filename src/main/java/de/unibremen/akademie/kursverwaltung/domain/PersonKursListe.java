package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class PersonKursListe {

    static public final PersonKursListe modelKP = new PersonKursListe();

    //static public final ObservableList<PersonKurs> personKursList =
           // FXCollections.observableArrayList();
//Mohamed
public static Boolean addPersonInKursAlsTeilnehmer(Person person, Kurs kurs) {

    for (PersonKurs personKurs : KvModel.personKursList) {
        if (personKurs.getPerson().equals(person) && personKurs.getKurs().equals(kurs)) {

            return false;
        }
    }
    PersonKurs personKurs = new PersonKurs();
    personKurs.setPerson(person);
    personKurs.setKurs(kurs);
    personKurs.setTeilnehmer(true);

    KvModel.personKursList.add(personKurs);
        return true;
    }


}
