package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class KvModel {
    private final String VERWALTUNGSDATEI = "src/main/resources/de/unibremen/akademie/kursverwaltung/storage/gespeicherteObjekte";

    static public final ObservableList<Person> personList =
            FXCollections.observableArrayList();
    static public final ObservableList<Kurs> kursList =
            FXCollections.observableArrayList();
    static public final KvModel model = new KvModel();
    static public Kurs aktuelleKurs ;


    private KvModel() {
    }

    public void load() {
        load(VERWALTUNGSDATEI);
    }
    public void load(String speicherPfad) {
        try {
            FileInputStream infile = new FileInputStream(speicherPfad);
            ObjectInputStream input = new ObjectInputStream(infile);
            // ObservableList is not Serializable. We have to work around
            personList.addAll((ArrayList<Person>) input.readObject());
            kursList.addAll((ArrayList<Kurs>) input.readObject());
            input.close();
        } catch (FileNotFoundException e) {
            System.err.print("Die Datei zum Lesen der Daten kann nicht gefunden werden! Fehlermeldung: ");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.print("Die Daten können nicht aus der Datei gelesen werden!  Fehlermeldung: ");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.print("Falsche Klasse in der Datei! Fehlermeldung: ");
            System.err.println(e.getMessage());
        }
    }

    public void save() {
        save(VERWALTUNGSDATEI);
    }
    public void save(String speicherPfad) {
        try {
            FileOutputStream outfile = new FileOutputStream(speicherPfad);
            ObjectOutputStream output = new ObjectOutputStream(outfile);
            // ObservableList is not Serializable. We have to work around
            output.writeObject(new ArrayList<Person>(personList));
            output.writeObject(new ArrayList<Kurs>(kursList));
            output.close();
        } catch (FileNotFoundException e) {
            System.err.print("Die Datei zum Schreiben der Daten kann nicht erstellt werden! Fehlermeldung: ");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.print("Die Daten können nicht in die Datei gespeichert werden!  Fehlermeldung: ");
            System.err.println(e.getMessage());
        }
    }

    public Kurs addnewKurs(String name, int anzahlTage, int zyklus, Date startDatum, int minTnZahl, int maxTnZahl,
                           double gebuehrBrutto, double mwstProzent, String kursBeschreibung) {
        Kurs kurs = new Kurs();
        if (!kurs.setName(name)) {
            throw new IllegalArgumentException("Name ist Falsch");
        }
        if (!kurs.setAnzahlTage(anzahlTage)) {
            throw new IllegalArgumentException("minimum teilnahme ist falsch");
        }
        if (!kurs.setZyklus(zyklus)) {
            throw new IllegalArgumentException("zyklus is Require");
        }
        if (!kurs.setStartDatum(startDatum)) {
            throw new IllegalArgumentException(" Start Date is Require");
        }
        if (!kurs.setMinTnZahl(minTnZahl)) {
            throw new IllegalArgumentException("minimum teilnahme ist falsch");
        }
        if (!kurs.setMaxTnZahl(maxTnZahl)) {
            throw new IllegalArgumentException(" Max anzahl darf nicht weniger als Min anzahl der Teilnehmer");
        }
        if (!kurs.setGebuehrBrutto(gebuehrBrutto)) {
            throw new IllegalArgumentException("gebuhr Brutto ist falsch");
        }
        if (!kurs.setMwstProzent(mwstProzent)) {
            throw new IllegalArgumentException("prozent MWST is Require");
        }

        kurs.setKursBeschreibung(kursBeschreibung);
        kurs.setEndeDatum(startDatum, zyklus, anzahlTage);
        kurs.setGebuehrNetto(gebuehrBrutto, mwstProzent);
        kurs.setMwstEuro(mwstProzent, gebuehrBrutto);
        kurs.setAktuelleTnZahl();
        if (!kurs.setFreiePlaetze()) {
            throw new IllegalArgumentException("Alles Voll");
        }
        kurs.setStatus();
        kursList.add(kurs);
        return kurs;
    }

    public void addPerson(String anrede, String titel, String name, String vorname, String strasse, String plz, String ort, String email, String telefon) {
        Person person = new Person();
        if (Person.checkIsEmpty(name) && Person.checkIsEmpty(vorname) && Person.checkValidEmail(email)) {
            person.setAnrede(anrede);
            person.setTitel(titel);
            person.setVorname(vorname);
            person.setName(name);
            person.setStrasse(strasse);
            person.setPlz(plz);
            person.setOrt(ort);
            person.setEmail(email);
            person.setTelefon(telefon);
            personList.add(person);
        }
    }

}
