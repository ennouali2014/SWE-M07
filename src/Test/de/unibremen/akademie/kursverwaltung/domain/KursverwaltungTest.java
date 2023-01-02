package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KursverwaltungTest {
    Kursverwaltung kursverwaltung = Kursverwaltung.model;// new Kursverwaltung();
    @Test
    void addnewKursTest() {
        kursverwaltung.addnewKurs("php",12,3,new Date(1672963200000L),12,2,150,19,"php backend");
        assertEquals(1,kursverwaltung.getKursList().size());
        kursverwaltung.addnewKurs("php",12,3,new Date(1672963200000L),2,8,150,19,"php backend");
        assertEquals(2,kursverwaltung.getKursList().size());
    }
}