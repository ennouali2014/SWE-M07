package de.unibremen.akademie.kursverwaltung.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kursverwaltung {
    private static List<Person> personList = new ArrayList<>();
    private static List<Kurs> kursList = new ArrayList<>();

    static Kursverwaltung model = new Kursverwaltung();

    public Kursverwaltung() {

    }

    public Kurs addnewKurs(String name, int anzahlTage, int zyklus, Date startDatum, int maxTnZahl, int minTnZahl,
                             double gebuehrBrutto, double mwstProzent, String kursBeschreibung) {
        Kurs kurs = new Kurs();
        if(!kurs.setName(name)){throw new IllegalArgumentException("Name ist Falsch");}
        if(!kurs.setAnzahlTage(anzahlTage)){ throw new IllegalArgumentException("minimum teilnahme ist falsch");}
        if(!kurs.setZyklus(zyklus)){throw new IllegalArgumentException("zyklus is Require");}
        if(!kurs.setStartDatum(startDatum)){throw new IllegalArgumentException(" Start Date is Require");}
        if(!kurs.setMinTnZahl(minTnZahl)){throw new IllegalArgumentException("minimum teilnahme ist falsch");}
        if(!kurs.setMaxTnZahl(maxTnZahl)){throw new IllegalArgumentException(" Max anzahl darf nicht weniger als Min anzahl der Teilnehmer");}

        if(!kurs.setGebuehrBrutto(gebuehrBrutto)){throw new IllegalArgumentException("gebuhr Brutto ist falsch");}
        if(!kurs.setMwstProzent(mwstProzent)){throw new IllegalArgumentException( "prozent MWST is Require");}

        kurs.setKursBeschreibung(kursBeschreibung);
        kurs.setEndeDatum(startDatum, zyklus, anzahlTage);
        kurs.setGebuehrNetto(gebuehrBrutto, mwstProzent);
        kurs.setMwstEuro(mwstProzent, gebuehrBrutto);
        kurs.setAktuelleTnZahl();
        kurs.setStatus();
        kursList.add(kurs);
        return kurs;

    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Kurs> getKursList() {
        return kursList;
    }

    public void setKursList(List<Kurs> kursList) {
        this.kursList = kursList;
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
