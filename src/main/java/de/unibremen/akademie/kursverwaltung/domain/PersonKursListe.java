package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PersonKursListe {


    public final ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();


    // TODO: wird jetzt nicht unbedingt nur geADDed, sondern ggf. geändert. Sollte das besser in einer anderen Methode gemacht werden? (AxF)

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

    /*public Boolean updatePersonInKurs(Person person, Kurs kurs, Boolean alsTeilnehmer){
        if(person.equals(personKursList)){

        }
        return true;
    }*/

    public Boolean addPersonInKursAlsInteressent(Person person, Kurs kurs) {
        return addPersonInKurs(person, kurs, false);
    }

    public Boolean addPersonInKursAlsTeilnehmer(Person person, Kurs kurs) {
        return addPersonInKurs(person, kurs, true);
    }

    public List<String> getKurseNames(Person person, boolean alsTeilnehmer) {
        // TODO: Ist kürzer, aber auch besser?
        return getKurse(person, alsTeilnehmer).stream()
                .map(c -> c.getName()).toList();
        // Alter code ...
        /*
        List<String> listkurs = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getPerson().equals(person) && personKurs.isTeilnehmer() == alsTeilnehmer) {
                listkurs.add(personKurs.getKurs().getName());
            }
        }
        return listkurs;
        */
    }

    public List<Kurs> getKurse(Person person, boolean alsTeilnehmer) {
        // TODO: Ist kürzer, aber auch besser?
        return getPersonKurse(person, alsTeilnehmer)
                .stream()
                .map(PersonKurs::getKurs)
                .toList();
        // Alter code ...
        /*
        List<Kurs> listkurs = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getPerson().equals(person) && personKurs.isTeilnehmer() == alsTeilnehmer) {
                listkurs.add(personKurs.getKurs());
            }
        }
        return listkurs;
        */
    }

    public List<PersonKurs> getPersonKurse(Person person, boolean alsTeilnehmer) {
        // Geht auch einfach aber ungewöhnlich
        // return personKursList.stream().filter(pk -> pk.getPerson().equals(person) && pk.isTeilnehmer() == alsTeilnehmer).toList();
        List<PersonKurs> listkurs = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getPerson().equals(person) && personKurs.isTeilnehmer() == alsTeilnehmer) {
                listkurs.add(personKurs);
            }
        }
        return listkurs;
    }

    public List<String> getKurseAlsTeilnehmer(Person person) {
        return getKurseNames(person, true);
    }

    public List<String> getKurseAlsInteressent(Person person) {
        return getKurseNames(person, false);
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

    public void removeAllKurseAlsInteressent(Person aktuellePerson) {
        removeAllKurse(aktuellePerson, false);
    }

    public void removeAllKurseAlsTeilnehmer(Person aktuellePerson) {
        removeAllKurse(aktuellePerson, true);
    }

    public void removeAllKurse(Person p, boolean alsTeilnehmer) {
        List<PersonKurs> list = getPersonKurse(p, alsTeilnehmer);
        for (PersonKurs k : list) {
            personKursList.remove(k);
        }
    }

    public int size() {
        if (personKursList == null) {
            throw new NullPointerException("List is null");
        }
        int size = 0;
        for (PersonKurs element : personKursList) {
            size++;
        }
        return size;

    }
}