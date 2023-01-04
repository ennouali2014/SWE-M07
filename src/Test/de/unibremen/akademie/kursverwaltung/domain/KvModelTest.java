package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KvModelTest {
    KvModel model = KvModel.model;
    String VERWALTUNGSDATEI = "src/test/resources/storage/gespeicherteTestObjekte";

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
        //load
        System.out.println("LOAD");
        model.load("src/test/resources/storage/gespeicherteTestObjekte");
        System.out.println("SAVE");
        model.save("src/test/resources/storage/gespeicherteTestObjekte");

        System.out.println(model.personList.size());
        System.out.println(model.kursList.size());
        model.kursList.clear();
        System.out.println(model.kursList.size());

    }
}