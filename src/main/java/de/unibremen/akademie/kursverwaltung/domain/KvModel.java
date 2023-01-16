package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static de.unibremen.akademie.kursverwaltung.domain.Kurs.addNewKurs;
import static de.unibremen.akademie.kursverwaltung.domain.Person.addNewPerson;

public class KvModel {
    private final String VERWALTUNGSDATEI = "src/main/resources/de/unibremen/akademie/kursverwaltung/storage/gespeicherteObjekte";

    static public final ObservableList<Person> personList =
            FXCollections.observableArrayList();
    static public final ObservableList<Kurs> kursList =
            FXCollections.observableArrayList();
    static public final KvModel model = new KvModel();
    static public Kurs aktuelleKurs ;
    static public Person aktuellePerson;


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

        //Falls keine Daten nach dem Laden vorhanden sind, werden Daten automatisch angelegt
        standardDatenErstellen();
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

    private void standardDatenErstellen() {
        //Falls keine Daten nach dem Laden vorhanden sind, werden Daten automatisch angelegt
        if (KvModel.personList.size() <= 0) {
            Person person = new Person();
            addNewPerson("Frau", "Prof. Dr.", "Hanna", "Müller", "Kohlweg 17", "28195", "Bremen", "hm@mail.com", "0421 978 98 45");
            System.out.println("Person-Standarddaten wurde geladen!");
        }
        if (KvModel.kursList.size() <= 0) {
            Kurs kurs = new Kurs();
            addNewKurs("PHP-Einsteiger", 14, 2, new Date(1709852800000L), 3, 6, 199.00, 19.0, "PHP für Dummies");
            System.out.println("Kurs-Standarddaten wurde geladen!");
        }
    }
}
