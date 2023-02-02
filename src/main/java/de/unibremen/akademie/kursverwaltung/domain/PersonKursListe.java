package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PersonKursListe {


    public final ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();


    // TODO: wird jetzt nicht unbedingt nur geADDed, sondern ggf. geändert. Sollte das besser in einer anderen Methode gemacht werden? (AxF)
    //TODO: personKurs.getKurs() ist manchmal null, Methode muss erneut instialisiert werden! Mohammed
    public Boolean addPersonInKurs(Person person, Kurs kurs, Boolean alsTeilnehmer) {
        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getPerson().equals(person) && personKurs.getKurs().equals(kurs)) {
                if (personKurs.isTeilnehmer() == alsTeilnehmer) {
                    return false;
                } else {
                    personKurs.setTeilnehmer(alsTeilnehmer);
                    return true;
                }
            }
        }
        PersonKurs personKurs = new PersonKurs();
        personKurs.setPerson(person);
        personKurs.setKurs(kurs);
        personKurs.setTeilnehmer(alsTeilnehmer);

        personKursList.add(personKurs);
        return true;
    }

    public Boolean addPersonInKursAlsInteressent(Person person, Kurs kurs) {
        return addPersonInKurs(person, kurs, false);
    }

    public Boolean addPersonInKursAlsTeilnehmer(Person person, Kurs kurs) {
        return addPersonInKurs(person, kurs, true);
    }

    public List<String> getKurse(Person person, boolean alsTeilnehmer) {
        List<String> listkurs = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getPerson().equals(person) && personKurs.isTeilnehmer() == alsTeilnehmer) {
                listkurs.add(personKurs.getKurs().getName());
            }
        }
        return listkurs;
    }

    public List<String> getKurseAlsTeilnehmer(Person person) {
        return getKurse(person, true);
    }

    public List<String> getKurseAlsInteressent(Person person) {
        return getKurse(person, false);
    }


    public void addKurse(Person person, List<Kurs> liste, boolean alsTeilnehmer) {
        for (var kurs : liste) {
            addPersonInKurs(person, kurs, alsTeilnehmer);
        }
    }

    public void addKurseAlsTeilnehmer(Person person, List<Kurs> teilnnehmerliste) {
        addKurse(person, teilnnehmerliste, true);
    }

    public void addKurseAlsInteressent(Person person, List<Kurs> interessentenliste) {
        addKurse(person, interessentenliste, false);
    }
}
