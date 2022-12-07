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
    }

    @Test
    void setMwstProzent() {
    }

    @Test
    void setKursBeschreibung() {
    }

    @Test
    void setInteressentenListe() {
    }

    @Test
    void setTeilnehmerListe() {
         kurs1.setTeilnehmerListe(ali);
         assertEquals(1,kurs1.getTeilnehmerListe().size());
         assertEquals(false,kurs1.setTeilnehmerListe(null));
    }
}