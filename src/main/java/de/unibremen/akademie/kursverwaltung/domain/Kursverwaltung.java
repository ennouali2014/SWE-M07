package de.unibremen.akademie.kursverwaltung.domain;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kursverwaltung {
    private static List<Person> personList = new ArrayList<>();
    private static List<Kurs> kursList = new ArrayList<>();

    static Kursverwaltung model = new Kursverwaltung();

    public Kursverwaltung() {
        kursList = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public String addnewKurs(String name, int anzahlTage, int zyklus, Date startDatum, int maxTnZahl, int minTnZahl,
                             double gebuehrBrutto, double mwstProzent, String kursBeschreibung) {
        Kurs kurs = new Kurs();
        kurs.setKursBeschreibung(kursBeschreibung);

        if(!kurs.setName(name)){return "Name is Require";}
        if(!kurs.setAnzahlTage(anzahlTage)){return "minimum teilnahme ist falsch";}
        if(!kurs.setZyklus(zyklus)){return "zyklus is Require";}
        if(!kurs.setStartDatum(startDatum)){return " Start Date is Require";}
        if(!kurs.setMaxTnZahl(maxTnZahl)){return " Max anzahl darf nicht weniger als Min anzahl der Teilnehmer";};
        if(!kurs.setMinTnZahl(minTnZahl)){return "minimum teilnahme ist falsch";}
        if(!kurs.setGebuehrBrutto(gebuehrBrutto)){return "gebuhr Brutto ist falsch";}
        if(!kurs.setMwstProzent(mwstProzent)){return "prozent MWST is Require";}

        kurs.setEndeDatum(startDatum, zyklus, anzahlTage);
        kurs.setGebuehrNetto(gebuehrBrutto, mwstProzent);
        kurs.setMwstEuro(mwstProzent, gebuehrBrutto);
        kurs.setAktuelleTnZahl();
        kursList.add(kurs);
        return "OK";

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
