package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KvModelTest {
    KvModel model = KvModel.model;

    @Test
    void addnewKursTest() {
        model.addnewKurs("php", 12, 3, new Date(1672963200000L), 12, 2, 150, 19, "php backend");
        //Assertions.assertEquals("Max anzahl darf nicht weniger als Min anzahl der Teilnehmer", thrown.getMessage());
        assertEquals(1, model.kursList.size());
        model.addnewKurs("php", 12, 3, new Date(1672963200000L), 2, 8, 150, 19, "php backend");
        assertEquals(2, model.kursList.size());
    }

    @Test
    void saveLoadtest() {
        model.addPerson("Herr", "Dr. rer. nat.", "Förster", "Alexander", "Feldweg 17", "28195", "Bremen", "axf@uni-bremen.de", "+49 162 175 978 23");
        model.addnewKurs("php", 12, 3, new Date(1672963200000L), 1, 10, 150, 19, "php backend");
        model.save("src/test/resources/storage/gespeicherteTestObjekte");
        model.kursList.clear();
        model.personList.clear();
        model.load("src/test/resources/storage/gespeicherteTestObjekte");
        assertEquals(model.personList.toString(), "[Person{anrede='StringProperty [value: Herr]'titel='StringProperty [value: Dr. rer. nat.]'name='StringProperty [value: Förster]', vorname='StringProperty [value: Alexander]', strasse='StringProperty [value: Feldweg 17]', plz='StringProperty [value: 28195]', ort='StringProperty [value: Bremen]', email='StringProperty [value: axf@uni-bremen.de]', telefon='StringProperty [value: +49 162 175 978 23]'}]");
        assertEquals(model.kursList.toString(), "[Kurs{name=php, anzahlTage=12, zyklus=3, startDatum=1672963200000, minTnZahl=1, maxTnZahl=10, gebuehrBrutto=150.0, mwstProzent=19.0, kursBeschreibung=php backend, endeDatum=1675382400000, status=Aktiv, gebuehrNetto=121.50000000000001, mwstEuro=28.5, freiePlaetze=10, aktuelleTnZahl=0}]");
    }

}