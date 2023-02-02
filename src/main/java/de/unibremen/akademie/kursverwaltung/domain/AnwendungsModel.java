package de.unibremen.akademie.kursverwaltung.domain;

public class AnwendungsModel extends KvModel {
    private final String SPEICHERPFAD = "target/storage/";
    private final String VERWALTUNGSDATEI = "gespeicherteObjekte";
    public static final AnwendungsModel kvModel = new AnwendungsModel();
    public Kurs aktuellerKurs;
    public Person aktuellePerson;

    private AnwendungsModel() {

    }

    public void load() {
        load(SPEICHERPFAD, VERWALTUNGSDATEI);
    }

    public void save() {
        save(SPEICHERPFAD, VERWALTUNGSDATEI);
    }


}
