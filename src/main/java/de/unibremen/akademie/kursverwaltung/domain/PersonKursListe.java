package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class PersonKursListe {

    static public final PersonKursListe modelKP = new PersonKursListe();

    static public final ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();

    private final String PERSONKURSSDATEI = "src/main/resources/de/unibremen/akademie/kursverwaltung/storage/gespeicherteObjekteKurs";

    public Boolean addPersonInKursAlsTeilnehmer(Person person, Kurs kurs) {

        for (PersonKurs personKurs : this.personKursList) {
            if (personKurs.getPerson().equals(person) && personKurs.getKurs().equals(kurs)) {
                System.out.println("Ich bin hier1" + personKursList.size());
                return false;
            }
        }
        PersonKurs personKurs = new PersonKurs(person, kurs, true);
        System.out.println("Ich bin hier2" + personKursList.size());
        this.personKursList.add(personKurs);
        return true;
    }

    public void load() {
        load(PERSONKURSSDATEI);
    }

    public void load(String speicherPfad) {
        try {
            FileInputStream infile = new FileInputStream(speicherPfad);
            ObjectInputStream input = new ObjectInputStream(infile);
            // ObservableList is not Serializable. We have to work around
            personKursList.addAll((ArrayList<PersonKurs>) input.readObject());
            System.out.println("-------LOAD-----" + personKursList.size());
            input.close();
        } catch (FileNotFoundException e) {
            System.err.print("list nicht gefunden ");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.print(" Fehlermeldung: ");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.print("Falsche Klasse in der Datei! Fehlermeldung: ");
            System.err.println(e.getMessage());
        }

    }

    public void save() {
        save(PERSONKURSSDATEI);
    }

    public void save(String speicherPfad) {
        try {
            FileOutputStream outfile = new FileOutputStream(speicherPfad);
            ObjectOutputStream output = new ObjectOutputStream(outfile);
            // ObservableList is not Serializable. We have to work around
            output.writeObject(new ArrayList<PersonKurs>(personKursList));
            System.out.println("-------SAVE-----" + personKursList.toString());
            output.close();
        } catch (FileNotFoundException e) {
            System.err.print("Die Datei zum Schrwerden! Fehlermeldung: ");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.print("Die Daten können niermeldung: ");
            System.err.println(e.getMessage());
        }
    }
}
