package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KvModelTest {
    KvModel testModel;

    @BeforeEach
    void beforeEach() {
        testModel = new KvModel();
    }

    private final String VERWALTUNGSDATEITEST = "src/test/resources/storage/gespeicherteTestObjekte";


    @Test
    void saveLoadtest() {
        Person person = testModel.getPersonen().addNewPerson("Herr", "Dr. rer. nat.", "Alexander", "Förster", "Feldweg 17", "28195", "Bremen", "axf@uni-bremen.de", "+49 162 175 978 23");
        Kurs kurs = testModel.getKurse().addNewKurs("php", 12, 3, new Date(1898553600000L), 1, 10, 150, 19, "php backend", "Aktiv");
        testModel.getPkListe().addPersonInKursAlsTeilnehmer(testModel.getPersonen().getPersonVonPersonenListe(0), testModel.getKurse().getKursVonKursListe(0));
        testModel.save(VERWALTUNGSDATEITEST);
        testModel.getKurse().getKursListe().clear();
        testModel.getPersonen().getPersonenListe().clear();
        testModel.load(VERWALTUNGSDATEITEST);
        assertEquals(1, testModel.getPersonen().getPersonenListe().size());
        assertEquals(1, testModel.getKurse().getKursListe().size());
        assertEquals(testModel.getPersonen().getPersonenListe().get(0).toString(), person.toString());
        assertEquals(testModel.getKurse().getKursListe().get(0).toString(), kurs.toString());
    }

}