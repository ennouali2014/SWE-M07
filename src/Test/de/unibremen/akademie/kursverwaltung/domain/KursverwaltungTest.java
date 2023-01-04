package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KursverwaltungTest {
    static final ObservableList<Person> personList =
            FXCollections.observableArrayList();

    static final ObservableList<Kurs> kursList =
            FXCollections.observableArrayList();
    Kursverwaltung model = Kursverwaltung.model;
    String VERWALTUNGSDATEI = "src/test/resources/storage/gespeicherteTestObjekte";

    @Test
    void addnewKursTest() {
        model.addnewKurs("php", 12, 3, new Date(1672963200000L), 12, 2, 150, 19, "php backend");
        assertEquals(1, model.kursList.size());
        model.addnewKurs("php", 12, 3, new Date(1672963200000L), 2, 8, 150, 19, "php backend");
        assertEquals(2, model.kursList.size());
    }

    @Test
    void saveLoadtest() {
        model.addPerson(Anrede.HERR, "Dr. rer. nat.", "Förster", "Alexander", "Feldweg 17", "28195", "Bremen", "axf@uni-bremen.de", "+49 162 175 978 23");
        model.addnewKurs("php", 12, 3, new Date(1672963200000L), 1, 10, 150, 19, "php backend");
        //load
       /* try {
            FileInputStream infile = new FileInputStream(VERWALTUNGSDATEI);
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

        //save
        try {
            FileOutputStream outfile = new FileOutputStream(VERWALTUNGSDATEI);
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
        }*/

        System.out.println(model.personList);
        System.out.println(model.kursList);

    }
}