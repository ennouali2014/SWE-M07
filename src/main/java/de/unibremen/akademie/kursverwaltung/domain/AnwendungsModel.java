package de.unibremen.akademie.kursverwaltung.domain;

public class AnwendungsModel extends KvModel {
    private final String VERWALTUNGSDATEI = "src/main/resources/de/unibremen/akademie/kursverwaltung/storage/gespeicherteObjekte";
    public static final AnwendungsModel kvModel = new AnwendungsModel();
    public Kurs aktuellerKurs;
    public Person aktuellePerson;

    private AnwendungsModel() {

    }

    public void load() {
        load(VERWALTUNGSDATEI);
    }

    public void save() {
        save(VERWALTUNGSDATEI);
    }


}
