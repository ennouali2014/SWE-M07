package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class KursTest {
    //Person ali=Person.addPerson(Anrede.HERR.name(), "Dr." ,"karakoese", "ali", "Stuhrestaße 2","28255", "Bremen", "ali@gmail.com", "01754848457");
    Person ali = new Person();
    Person tn1 = new Person();
    Person tn2 = new Person();
    Person tn3 = new Person();
    Person int1 = new Person();
    Kurs kurs1 = new Kurs();
    Date date1 = new Date(1672963200000L); // 06.01.23
    Date date2 = new Date(1669852800000L); // 01.12.22

    @Test
    void addNewKursTest() {
//        Kurs kurs = Kurs.addNewKurs("php", 12, 3, new Date(1672963200000L), 2, 8, 150, 19, "php backend");
//        Kurs.addNewKurs("php", 12, 3, new Date(1672963200000L), 12, 2, 150, 19, "php backend");
//        Assertions.assertEquals("Max anzahl darf nicht weniger als Min anzahl der Teilnehmer", thrown.getMessage());
//        assertEquals(1, kvModel.kursList.size());
//        Kurs.addNewKurs("php", 12, 3, new Date(1672963200000L), 2, 8, 150, 19, "php backend");
//        assertEquals(2, KvModel.kursList.size());
    }

    @Test
    void setName() {
        assertTrue(kurs1.setName("c"));
        assertFalse(kurs1.setName(""));
        assertFalse(kurs1.setName(null));
    }

    @Test
    void setAnzahlTage() {
        assertFalse(kurs1.setAnzahlTage(0));
        assertFalse(kurs1.setAnzahlTage(-1));
        assertTrue(kurs1.setAnzahlTage(2));
    }

    @Test
    void setZyklus() {
    }

    @Test
    void setStartDatum() {

        assertTrue(kurs1.setStartDatum(date1));
        assertFalse(kurs1.setStartDatum(date2));

    }


    @Test
    void setEndeDatum() {
        Date date3 = new Date(1677196800000L); // 24.02.23
        kurs1.setEndeDatum();
        assertEquals(date3, kurs1.getEndeDatum());
    }

    @Test
    void setAktuelleTnZahl() {
        kurs1.setTeilnehmerListe(ali);
        kurs1.setTeilnehmerListe(tn1);
        kurs1.setTeilnehmerListe(tn2);
        kurs1.setTeilnehmerListe(tn3);
        kurs1.setAktuelleTnZahl();
        assertEquals(4, kurs1.getAktuelleTnZahl());
    }

    @Test
    void setMinTnZahl() {
        kurs1.setMinTnZahl(15);
        assertTrue(kurs1.setMinTnZahl(5));
        assertTrue(kurs1.setMinTnZahl(20));
        assertFalse(kurs1.setMinTnZahl(0));
        assertFalse(kurs1.setMinTnZahl(-2));

    }

    @Test
    void setMaxTnZahl() {
        kurs1.setMinTnZahl(5);
        kurs1.setMaxTnZahl(20);

        assertEquals(20, kurs1.getMaxTnZahl());
        assertFalse(kurs1.setMaxTnZahl(-1));
        assertTrue(kurs1.setMaxTnZahl(10));
    }

    @Test
    void setFreiePlaetze() {
//        kurs1.setTeilnehmerListe(tn1);
//        kurs1.setTeilnehmerListe(tn2);
//        kurs1.setTeilnehmerListe(tn3);
//
//        kurs1.setAktuelleTnZahl();
//
//        kurs1.setMaxTnZahl(2);
//        assertFalse(kurs1.setFreiePlaetze());
//
//
//        kurs1.setMaxTnZahl(10);
//        kurs1.setAktuelleTnZahl();
//        kurs1.setFreiePlaetze();
//        assertEquals(7, kurs1.getFreiePlaetze());
    }

    @Test
    void setGebuehrBrutto() {
        assertTrue(kurs1.setGebuehrBrutto(0.01));
        assertFalse(kurs1.setGebuehrBrutto(0.00));
        assertFalse(kurs1.setGebuehrBrutto(-0.01));
    }


    @Test
    void setMwstProzent() {
        assertTrue(kurs1.setMwstProzent(0.01));
        assertTrue(kurs1.setMwstProzent(0.0));
        assertFalse(kurs1.setMwstProzent(-0.01));
    }

    @Test
    void setKursBeschreibung() {
        kurs1.setKursBeschreibung("ali");
        assertTrue(kurs1.setKursBeschreibung("ali"));
        assertTrue(kurs1.setKursBeschreibung(""));
        assertFalse(kurs1.setKursBeschreibung(null));
    }

    @Test
    void setInteressentenListe() {
        kurs1.setInteressentenListe(ali);
        assertEquals(1, kurs1.getInteressentenListe().size());
        assertFalse(kurs1.setInteressentenListe(null));
    }

    @Test
    void setTeilnehmerListe() {
        kurs1.setTeilnehmerListe(ali);
        assertEquals(1, kurs1.getTeilnehmerListe().size());
        assertFalse(kurs1.setTeilnehmerListe(null));
    }
}