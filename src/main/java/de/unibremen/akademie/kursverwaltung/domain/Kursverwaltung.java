package de.unibremen.akademie.kursverwaltung.domain;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kursverwaltung {
    private List<Person> personList=new ArrayList<>();
    private List<Kurs> kursList=new ArrayList<>();

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
        this.kursList.add(kurs);
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
}
