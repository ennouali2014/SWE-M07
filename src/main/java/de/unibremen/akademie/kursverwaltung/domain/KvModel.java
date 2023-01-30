package de.unibremen.akademie.kursverwaltung.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class KvModel {
    private final String VERWALTUNGSDATEI = "src/main/resources/de/unibremen/akademie/kursverwaltung/storage/gespeicherteObjekte";

    private PersonKursListe pkListe = new PersonKursListe();
    private PersonenListe personen = new PersonenListe();
    private KursListe kurse = new KursListe();


    public KvModel() {
    }

    public PersonenListe getPersonen() {
        return personen;
    }

    public KursListe getKurse() {
        return kurse;
    }

    public PersonKursListe getPkListe() { return pkListe; }

    public void load() {
        load(VERWALTUNGSDATEI);
    }


    public void load(String speicherPfad) {
        try {
            FileInputStream infile = new FileInputStream(speicherPfad);
            ObjectInputStream input = new ObjectInputStream(infile);
            // ObservableList is not Serializable. We have to work around
            personen.getPersonenListe().addAll((ArrayList<Person>) input.readObject());
            kurse.getKursListe().addAll((ArrayList<Kurs>) input.readObject());
            pkListe.personKursList.addAll((ArrayList<PersonKurs>) input.readObject());

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
            output.writeObject(new ArrayList<Person>(personen.getPersonenListe()));
            output.writeObject(new ArrayList<Kurs>(kurse.getKursListe()));
            output.writeObject(new ArrayList<PersonKurs>(pkListe.personKursList));

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
        if (personen.getPersonenListe().size() <= 0) {
            Person person = new Person();
            person.addNewPerson("Frau", "Prof. Dr.", "Hanna", "Müller", "Kohlweg 17", "28195", "Bremen", "hm@mail.com", "0421 978 98 45");
            person.addNewPerson("Herr", "Baron", "Konrad", "Mumpitz", "Sachsenstr. 9a", "D-34589", "Hannover", "baron-k-m@mail.de", "0511 7863548");
            person.addNewPerson("Divers", "", "Cosma", "Shiva", "Aurenallee 666", "21165", "Hamburg", "cosma_shiva@in-den-wolken.business", "040 666666666");
            person.addNewPerson("", "", "Manfred", "Schneider", "Kohlweg 17", "10978", "Berlin", "ms@hotmail.com", "+49 162 7898 56 45");
            person.addNewPerson("Frau", "Freifrau", "Julia", "von Bayern", "Wiesnplatz 96f", "84569", "München", "freifrau_jvb@brauhaus.bayern", "00 170 235 45 81");
            /*person.addNewPerson("Frau", "Prof. Dr.", "Hanna", "Müller", "Kohlweg 17", "28195", "Bremen", "hm@mail.com", "0421 978 98 45");
            person.addNewPerson("Herr", "Baron", "Konrad", "Mumpitz", "Sachsenstr. 9a", "D-34589", "Hannover", "baron-k-m@mail.de", "0511 7863548");
            person.addNewPerson("Divers", "", "Cosma", "Shiva", "Aurenallee 666", "21165", "Hamburg", "cosma_shiva@in-den-wolken.business", "040 666666666");
            person.addNewPerson("", "", "Manfred", "Schneider", "Kohlweg 17", "10978", "Berlin", "ms@hotmail.com", "+49 162 7898 56 45");
            person.addNewPerson("Frau", "Freifrau", "Julia", "von Bayern", "Wiesnplatz 96f", "84569", "München", "freifrau_jvb@brauhaus.bayern", "00 170 235 45 81");
            person.addNewPerson("Frau", "Prof. Dr.", "Hanna", "Müller", "Kohlweg 17", "28195", "Bremen", "hm@mail.com", "0421 978 98 45");
            person.addNewPerson("Herr", "Baron", "Konrad", "Mumpitz", "Sachsenstr. 9a", "D-34589", "Hannover", "baron-k-m@mail.de", "0511 7863548");
            person.addNewPerson("Divers", "", "Cosma", "Shiva", "Aurenallee 666", "21165", "Hamburg", "cosma_shiva@in-den-wolken.business", "040 666666666");
            person.addNewPerson("", "", "Manfred", "Schneider", "Kohlweg 17", "10978", "Berlin", "ms@hotmail.com", "+49 162 7898 56 45");
            person.addNewPerson("Frau", "Freifrau", "Julia", "von Bayern", "Wiesnplatz 96f", "84569", "München", "freifrau_jvb@brauhaus.bayern", "00 170 235 45 81");
            person.addNewPerson("Frau", "Prof. Dr.", "Hanna", "Müller", "Kohlweg 17", "28195", "Bremen", "hm@mail.com", "0421 978 98 45");
            person.addNewPerson("Herr", "Baron", "Konrad", "Mumpitz", "Sachsenstr. 9a", "D-34589", "Hannover", "baron-k-m@mail.de", "0511 7863548");
            person.addNewPerson("Divers", "", "Cosma", "Shiva", "Aurenallee 666", "21165", "Hamburg", "cosma_shiva@in-den-wolken.business", "040 666666666");
            person.addNewPerson("", "", "Manfred", "Schneider", "Kohlweg 17", "10978", "Berlin", "ms@hotmail.com", "+49 162 7898 56 45");
            person.addNewPerson("Frau", "Freifrau", "Julia", "von Bayern", "Wiesnplatz 96f", "84569", "München", "freifrau_jvb@brauhaus.bayern", "00 170 235 45 81");
            person.addNewPerson("Frau", "Prof. Dr.", "Hanna", "Müller", "Kohlweg 17", "28195", "Bremen", "hm@mail.com", "0421 978 98 45");
            person.addNewPerson("Herr", "Baron", "Konrad", "Mumpitz", "Sachsenstr. 9a", "D-34589", "Hannover", "baron-k-m@mail.de", "0511 7863548");
            person.addNewPerson("Divers", "", "Cosma", "Shiva", "Aurenallee 666", "21165", "Hamburg", "cosma_shiva@in-den-wolken.business", "040 666666666");
            person.addNewPerson("", "", "Manfred", "Schneider", "Kohlweg 17", "10978", "Berlin", "ms@hotmail.com", "+49 162 7898 56 45");
            person.addNewPerson("Frau", "Freifrau", "Julia", "von Bayern", "Wiesnplatz 96f", "84569", "München", "freifrau_jvb@brauhaus.bayern", "00 170 235 45 81");*/
            System.out.println("Person-Standarddaten wurde geladen!");
        }
        if (kurse.getKursListe().size() <= 0) {
            Kurs kurs = new Kurs();
            kurs.addNewKurs("PHP-Einsteiger", 14, 2, new Date(1900368000000L), 3, 6, 199.00, 19.0, "PHP für Dummies", "Aktiv");
            kurs.addNewKurs("Angular FE", 21, 3, new Date(1901232000000L), 2, 8, 849.00, 19.0, "Angular für Frontend-Entwickler", "Aktiv");
            kurs.addNewKurs("Arduino", 5, 5, new Date(1899763200000L), 10, 12, 79.00, 19.0, "Arduino für Kids und Großeltern", "geplant");
            kurs.addNewKurs("Deep S9", 32, 4, new Date(1911772800000L), 8, 9, 1275.00, 19.0, "Deep learning mit Python", "Abgesagt");
            kurs.addNewKurs("Web-Start", 7, 3, new Date(1920240000000L), 12, 15, 249.00, 19.0, "HTML und CSS zum Frühstück", "Aktiv");
            // startdatum 31.01.2023
            // kurs.addNewKurs("Web-Profi", 21, 3, new Date(1675123200000L), 1, 10, 299.00, 19.0, "HTML und CSS wie im Schlaf", "Aktiv");
            System.out.println("Kurs-Standarddaten wurde geladen!");
        }
        if (pkListe.personKursList.size() <= 0) {
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(0), kurse.getKursVonKursListe(0));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(0), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(0), kurse.getKursVonKursListe(2));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(1), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(2), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(3), kurse.getKursVonKursListe(1));
            /*pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(4), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(5), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(6), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(7), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(8), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(9), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(10), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(11), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(12), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(13), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(14), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(15), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(16), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(17), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(18), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(19), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(20), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(21), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(22), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(23), kurse.getKursVonKursListe(1));
            pkListe.addPersonInKursAlsTeilnehmer(personen.getPersonVonPersonenListe(24), kurse.getKursVonKursListe(1));*/
            System.out.println("PersonKursList-Standarddaten wurde geladen!");
        }
    }
}
