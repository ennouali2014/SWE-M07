package de.unibremen.akademie.kursverwaltung.domain;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KvModelTest {

    private final String VERWALTUNGSDATEITEST = "src/test/resources/storage/gespeicherteTestObjekte";


    @Test
    void saveLoadtest() {
        Person.addNewPerson("Herr", "Dr. rer. nat.", "Alexander", "Förster", "Feldweg 17", "28195", "Bremen", "axf@uni-bremen.de", "+49 162 175 978 23");
        Kurs.addNewKurs("php", 12, 3, new Date(1898553600000L), 1, 10, 150, 19, "php backend", "Aktiv");
        kvModel.getPkListe().addPersonInKursAlsTeilnehmer(kvModel.getPersonen().getPersonVonPersonenListe(0), kvModel.getKurse().getKursVonKursListe(0));
        kvModel.save(VERWALTUNGSDATEITEST);
        kvModel.getKurse().getKursListe().clear();
        kvModel.getPersonen().getPersonenListe().clear();
        kvModel.load(VERWALTUNGSDATEITEST);
        assertEquals(kvModel.getPersonen().getPersonenListe().toString(), "[Person{anrede='StringProperty [value: Herr]'titel='StringProperty [value: Dr. rer. nat.]'vorname='StringProperty [value: Alexander]', nachname='StringProperty [value: Förster]', strasse='StringProperty [value: Feldweg 17]', plz='StringProperty [value: 28195]', ort='StringProperty [value: Bremen]', email='StringProperty [value: axf@uni-bremen.de]', telefon='StringProperty [value: +49 162 175 978 23]'}]");
        assertEquals(kvModel.getKurse().getKursListe().toString(), "[Kurs{name=php, anzahlTage=12, zyklus=3, startDatum=1898553600000, minTnZahl=1, maxTnZahl=10, gebuehrBrutto=150.0, mwstProzent=19.0, kursBeschreibung=php backend, endeDatum=1900972800000, status=Aktiv, gebuehrNetto=121.50000000000001, mwstEuro=28.5, freiePlaetze=10, aktuelleTnZahl=0}]");
    }

}