package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void addnewKurs() {

    }

    @Test
    void setName() {
    }

    @Test
    void setAnzahlTage() {
        assertEquals(false,kurs1.setAnzahlTage(0));
        assertEquals(false,kurs1.setAnzahlTage(-1));
        assertEquals(true,kurs1.setAnzahlTage(2));
    }

    @Test
    void setZyklus() {
    }

    @Test
    void setStartDatum() {


        assertEquals(true, kurs1.setStartDatum(date1));
        assertEquals(false, kurs1.setStartDatum(date2));

    }


    @Test
    void setEndeDatum() {
        Date date3 = new Date(1677196800000L); // 24.02.23
        kurs1.setEndeDatum(date1, 3, 20);
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
        kurs1.setMaxTnZahl(15);
        assertEquals(true, kurs1.setMinTnZahl(5));
        assertEquals(false, kurs1.setMinTnZahl(17));
        assertEquals(false, kurs1.setMinTnZahl(0));
        assertEquals(false, kurs1.setMinTnZahl(-2));

    }

    @Test
    void setMaxTnZahl() {
    }

    @Test
    void setFreiePlaetze() {
    }

    @Test
    void setGebuehrBrutto() {
    }

    @Test
    void setGebuehrNetto() {
    }

    @Test
    void setMwstEuro() {
        assertEquals(true, kurs1.setMwstEuro(1.0, 1.0));
        assertEquals(false, kurs1.setMwstEuro(-1.0, 1.0));
        assertEquals(false, kurs1.setMwstEuro(1.0, -1.0));
    }

    @Test
    void setMwstProzent() {
        assertEquals(true, kurs1.setMwstProzent(1));
        assertEquals(false, kurs1.setMwstProzent(0));
        assertEquals(false, kurs1.setMwstProzent(-1));
    }

    @Test
    void setKursBeschreibung() {
    }

    @Test
    void setInteressentenListe() {
        kurs1.setInteressentenListe(ali);
        assertEquals(1, kurs1.getInteressentenListe().size());
        assertEquals(false, kurs1.setInteressentenListe(null));
    }

    @Test
    void setTeilnehmerListe() {
    }
}