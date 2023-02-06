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

                    if (alsTeilnehmer) {
                        if (kurs.getFreiePlaetze() == 0) {
                            Meldung.teilnehmerVoll("keine frei Plätze mehr für " + kurs.getName());
                            return false;
                        }

                        kurs.setAktuelleTnZahl(kurs.getAktuelleTnZahl() + 1);
                        kurs.setFreiePlaetze();
                    } else {
                        kurs.setAktuelleTnZahl(kurs.getAktuelleTnZahl() - 1);
                        kurs.setFreiePlaetze();

                    }
                    personKurs.setTeilnehmer(alsTeilnehmer);
                    return true;
                }
            }
        }
        PersonKurs personKurs = new PersonKurs();
        personKurs.setPerson(person);
        personKurs.setKurs(kurs);
        personKurs.setTeilnehmer(alsTeilnehmer);
        if (alsTeilnehmer) {
            if (kurs.getFreiePlaetze() == 0) {
                Meldung.teilnehmerVoll("keine frei Plätze mehr für " + kurs.getName());
                return false;
            }
            personKurs.getKurs().setAktuelleTnZahl(kurs.getAktuelleTnZahl() + 1);
            personKurs.getKurs().setFreiePlaetze();

        }

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
    public List<String> getPersonName(Kurs kurs, boolean alsTeilnehmer) {
        // TODO: Ist kürzer, aber auch besser?
        return getPersonen(kurs, alsTeilnehmer).stream()
                .map(c -> c.getNachname()).toList();
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


    public List<Person> getPersonen(Kurs kurs, boolean alsTeilnehmer) {
        // TODO: Ist kürzer, aber auch besser?
        return getKursPersonen(kurs, alsTeilnehmer)
                .stream()
                .map(PersonKurs::getPerson)
                .toList();


    }

    public List<PersonKurs> getKursPersonen(Kurs kurs, boolean alsTeilnehmer) {
        // Geht auch einfach aber ungewöhnlich
        // return personKursList.stream().filter(pk -> pk.getPerson().equals(person) && pk.isTeilnehmer() == alsTeilnehmer).toList();
        List<PersonKurs> listperson = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getKurs().equals(kurs) && personKurs.isTeilnehmer() == alsTeilnehmer) {
                listperson.add(personKurs);
            }
        }
        return listperson;
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

    public List<PersonKurs> getKursePerson(Kurs kurs, boolean alsTeilnehmer) {
        // Geht auch einfach aber ungewöhnlich
        // return personKursList.stream().filter(pk -> pk.getPerson().equals(person) && pk.isTeilnehmer() == alsTeilnehmer).toList();
        List<PersonKurs> listkurs = new ArrayList<>();

        for (PersonKurs personKurs : personKursList) {
            if (personKurs.getKurs().equals(kurs) && personKurs.isTeilnehmer() == alsTeilnehmer) {
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

    public List<Person> getPersonAlsTeilnehmer(Kurs kurs) {
        return getPersonen(kurs, true);
    }
    public List<String> getPersonNameAlsTeilnehmer(Kurs kurs) {
        return getPersonName(kurs, true);
    }

    public List<Person> getPersonAlsInteressent(Kurs kurs) {
        return getPersonen(kurs, false);
    }


    public void addKurse(Person person, List<Kurs> liste, boolean alsTeilnehmer) {
        for (var kurs : liste) {
            addPersonInKurs(person, kurs, alsTeilnehmer);
        }
    }
    public void addPersonen(Kurs kurs, List<Person> liste, boolean alsTeilnehmer) {
        for (var person : liste) {
            addPersonInKurs(person, kurs, alsTeilnehmer);
        }
    }

    public void addPerson(Kurs kurs, List<Person> liste, boolean alsTeilnehmer) {
        for (var person : liste) {
            addPersonInKurs(person, kurs, alsTeilnehmer);
        }
    }

    public void addPersonAlsTeilNehmer(Kurs kurs, List<Person> teilnehmerliste) {
        List<PersonKurs> list = getKursePerson(kurs, true);
        for (PersonKurs k : list) {
            if (!teilnehmerliste.contains(k.getPerson())) {
                removePerson(k.getPerson(), kurs, true);
                kurs.setAktuelleTnZahl(kurs.getAktuelleTnZahl() - 1);
                kurs.setFreiePlaetze();
            }
        }
        if (teilnehmerliste == null) {
            removeAllPersonenAlsTeilnehmer(kurs);
        }
        addPerson(kurs, teilnehmerliste, true);
    }

    public void addPerson(Kurs kurs, List<Person> liste, boolean alsTeilnehmer) {
        for (var person : liste) {
            addPersonInKurs(person, kurs, alsTeilnehmer);
        }
    }

    public void addPersonAlsTeilNehmer(Kurs kurs, List<Person> teilnehmerliste) {
        List<PersonKurs> list = getKursePerson(kurs, true);
        for (PersonKurs k : list) {
            if (!teilnehmerliste.contains(k.getPerson())) {
                removePerson(k.getPerson(), kurs, true);
                kurs.setAktuelleTnZahl(kurs.getAktuelleTnZahl() - 1);
                kurs.setFreiePlaetze();
            }
        }
        if (teilnehmerliste == null) {
            removeAllPersonenAlsTeilnehmer(kurs);
        }
        addPerson(kurs, teilnehmerliste, true);
    }

    public void addKurseAlsTeilnehmer(Person person, List<Kurs> teilnehmerliste) {
        List<PersonKurs> list = getPersonKurse(person, true);
        for (PersonKurs k : list) {
            if (!teilnehmerliste.contains(k.getKurs())) {
                removeKurse(person, k.getKurs(), true);
                k.getKurs().setAktuelleTnZahl(k.getKurs().getAktuelleTnZahl() - 1);
                k.getKurs().setFreiePlaetze();
            }
        }
        if (teilnehmerliste == null) {
            removeAllKurseAlsTeilnehmer(person);
        }
        addKurse(person, teilnehmerliste, true);
    }

    /*public void addPersonAlsTeilnehmer(Kurs kurs, List<Person> teilnehmerliste) {
        List<PersonKurs> list = getKursePerson(kurs, true);
        for (PersonKurs k : list) {
            if (!teilnehmerliste.contains(k.getKurs())) {
                removeKurse(kurs, k.getKurs(), true);
                k.getKurs().setAktuelleTnZahl(k.getKurs().getAktuelleTnZahl() - 1);
                k.getKurs().setFreiePlaetze();
            }
        }
        if (teilnehmerliste == null) {
            removeAllKurseAlsTeilnehmer(kurs);
        }
        addPersonen(kurs, teilnehmerliste, true);
    }*/

    public void addKurseAlsInteressent(Person person, List<Kurs> interessentenliste) {
        addKurse(person, interessentenliste, false);
    }



    public void removeAllKurseAlsInteressent(Person aktuellePerson) {
        removeAllKurse(aktuellePerson, false);
    }

    public void removeAllKurseAlsTeilnehmer(Person aktuellePerson) {
        removeAllKurse(aktuellePerson, true);
    }

    public void removeAllPersonenAlsTeilnehmer(Kurs aktuelleKurs) {
        removeAllPersonen(aktuelleKurs, true);
    }

    public void removeAllKurse(Person p, boolean alsTeilnehmer) {
        List<PersonKurs> list = getPersonKurse(p, alsTeilnehmer);
        for (PersonKurs k : list) {
            personKursList.remove(k);
        }
    }

    public void removeAllPersonen(Kurs kurs, boolean alsTeilnehmer) {
        List<PersonKurs> list = getKursePerson(kurs, alsTeilnehmer);
        for (PersonKurs k : list) {
            personKursList.remove(k);
        }
    }

    public void removeKurse(Person p, Kurs kurs, boolean alsTeilnehmer) {
        List<PersonKurs> list = getPersonKurse(p, alsTeilnehmer);
        for (PersonKurs k : list) {
            if (k.getKurs().equals(kurs)) {
                personKursList.remove(k);

            }
        }
    }

    public void removePerson(Person person, Kurs kurs, boolean alsTeilnehmer) {
        List<PersonKurs> list = getKursePerson(kurs, alsTeilnehmer);
        for (PersonKurs k : list) {
            if (k.getPerson().equals(person)) {
                personKursList.remove(k);
            }
        }

    }

    public void removeAll(Kurs kurs) {
        List<PersonKurs> listTeilnehmer = getKursePerson(kurs, true);
        List<PersonKurs> listInteressenter = getKursePerson(kurs, false);
        for (PersonKurs k : listTeilnehmer) {
            personKursList.remove(k);
        }
        for (PersonKurs k : listInteressenter) {
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

    @Override
    public String toString() {
        return "PersonKursListe{" +
                "personKursList=" + personKursList +
                '}';
    }
}