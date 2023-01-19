package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonKursListe {

    static public final PersonKursListe modelKP = new PersonKursListe();

    static public final ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();



    public List<String> getTeilnehmer(Person person){
        List<String> listkurs = new ArrayList<>();

        for(PersonKurs personKurs:KvModel.personKursList){
            if(personKurs.getPerson().equals(person)) {
                listkurs.add(personKurs.getKurs().getName());
            }
        }
        return listkurs;
    }
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

    personKursList.add(personKurs);
        return true;
    }


}
