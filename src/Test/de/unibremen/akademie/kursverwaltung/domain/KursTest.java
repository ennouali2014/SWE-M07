package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class KursTest {
    //Person ali=Person.addPerson(Anrede.HERR.name(), "Dr." ,"karakoese", "ali", "Stuhrestaße 2","28255", "Bremen", "ali@gmail.com", "01754848457");
    Person ali = new Person();
    Kurs kurs1 = new Kurs();
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
    }

    @Test
    void setEndeDatum() {
    }

    @Test
    void setAktuelleTnZahl() {
    }

    @Test
    void setMinTnZahl() {
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