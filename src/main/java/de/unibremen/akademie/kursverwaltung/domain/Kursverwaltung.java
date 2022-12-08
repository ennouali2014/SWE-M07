package de.unibremen.akademie.kursverwaltung.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kursverwaltung {
    private static List<Person> personList = new ArrayList<>();
    private static List<Kurs> kursList = new ArrayList<>();

    public Kursverwaltung() {
    }

    public static String addnewKurs(String name, int anzahlTage, int zyklus, Date startDatum, int maxTnZahl, int minTnZahl,
                                    double gebuehrBrutto, double mwstProzent, String kursBeschreibung, Person person, Person person1) {
        Kurs kurs = new Kurs();
        kurs.setKursBeschreibung(kursBeschreibung);
        if (kurs.setName(name) == false) {

        }
        ;
        kurs.setAnzahlTage(anzahlTage);
        kurs.setZyklus(zyklus);
        kurs.setStartDatum(startDatum);
        if (kurs.setMaxTnZahl(maxTnZahl) == false) {
            return "";
        }
        ;
        if (kurs.setMinTnZahl(minTnZahl) == false) {
            return "minimum teilnahme ist falsch";
        }
        if (kurs.setGebuehrBrutto(gebuehrBrutto) == false) {
            return "gebuhr Brutto ist falssch";
        }
        kurs.setMwstProzent(mwstProzent);
        kurs.setInteressentenListe(person);
        kurs.setEndeDatum(startDatum, zyklus, anzahlTage);
        kurs.setGebuehrNetto(gebuehrBrutto, mwstProzent);
        kurs.setMwstEuro(mwstProzent, gebuehrBrutto);
        kurs.setTeilnehmerListe(person);
        kurs.setInteressentenListe(person1);
        kurs.setAktuelleTnZahl();
        kursList.add(kurs);
        return null;
    }

    static public String addPerson(Anrede anrede, String name, String vorname, String strasse, String plz, String ort, String email, String telefon) {
        if (Person.checkIsEmpty(name) && Person.checkIsEmpty(vorname) && Person.checkValidEmail(email)) {
            Person person = new Person();
            person.setAnrede(anrede);
            person.setVorname(vorname);
            person.setName(name);
            person.setStrasse(strasse);
            person.setPlz(plz);
            person.setOrt(ort);
            person.setEmail(email);
            personList.add(person);


            return "Alles OK!";
        }

        return "Fehler! Daten wurden nicht gespeichert!";
    }


}
