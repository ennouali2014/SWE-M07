package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KvModelTest {
    KvModel model = KvModel.model;

    @Test
    void saveLoadtest() {
        Person person = Person.addNewPerson("Herr", "Dr. rer. nat.", "Alexander", "Förster", "Feldweg 17", "28195", "Bremen", "axf@uni-bremen.de", "+49 162 175 978 23");
        Kurs kurs = Kurs.addNewKurs("php", 12, 3, new Date(1672963200000L), 1, 10, 150, 19, "php backend");
        model.save("src/test/resources/storage/gespeicherteTestObjekte");
        KvModel.kursList.clear();
        KvModel.personList.clear();
        model.load("src/test/resources/storage/gespeicherteTestObjekte");
        assertEquals(KvModel.personList.toString(), "[Person{anrede='StringProperty [value: Herr]'titel='StringProperty [value: Dr. rer. nat.]'vorname='StringProperty [value: Alexander]', nachname='StringProperty [value: Förster]', strasse='StringProperty [value: Feldweg 17]', plz='StringProperty [value: 28195]', ort='StringProperty [value: Bremen]', email='StringProperty [value: axf@uni-bremen.de]', telefon='StringProperty [value: +49 162 175 978 23]'}]");
        assertEquals(KvModel.kursList.toString(), "[Kurs{name=php, anzahlTage=12, zyklus=3, startDatum=1672963200000, minTnZahl=1, maxTnZahl=10, gebuehrBrutto=150.0, mwstProzent=19.0, kursBeschreibung=php backend, endeDatum=1675382400000, status=Aktiv, gebuehrNetto=121.50000000000001, mwstEuro=28.5, freiePlaetze=10, aktuelleTnZahl=0}]");
    }

}