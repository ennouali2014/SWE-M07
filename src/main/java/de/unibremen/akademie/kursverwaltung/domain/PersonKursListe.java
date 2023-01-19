package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PersonKursListe {


    public final ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();


    public Boolean addPersonInKursAlsTeilnehmer(Person person, Kurs kurs) {
        for (PersonKurs personKurs : personKursList) {
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

    public List<String> getKurseAlsTeilnehmer(Person person) {
        List<String> listkurs = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getPerson().equals(person)) {
                listkurs.add(personKurs.getKurs().getName());
            }
        }
        return listkurs;
    }


    public void addKurseAlsTeilnehmer(Person person, List<Kurs> teilnnehmerliste) {
        for (var kurs : teilnnehmerliste) {
            addPersonInKursAlsTeilnehmer(person, kurs);
        }
    }
}
