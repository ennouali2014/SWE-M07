package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KursverwaltungTest {
    Kursverwaltung kursverwaltung = Kursverwaltung.model;// new Kursverwaltung();

    @Test
    void addnewKursTest() {
        kursverwaltung.addnewKurs("php", 12, 3, new Date(1672963200000L), 12, 2, 150, 19, "php backend");
        assertEquals(1, kursverwaltung.getKursList().size());
        kursverwaltung.addnewKurs("php", 12, 3, new Date(1672963200000L), 2, 8, 150, 19, "php backend");
        assertEquals(2, kursverwaltung.getKursList().size());
    }

    @Test
    void saveLoadtest() {
        Kursverwaltung.addPerson(Anrede.HERR, "Dr. rer. nat.", "Förster", "Alexander", "Feldweg 17", "28195", "Bremen", "axf@uni-bremen.de ", "+49 162 175 978 23");

    }
}