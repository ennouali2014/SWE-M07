package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KursListeTest {


    @Test
    void createKursListe() {
        KursListe testList = new KursListe();
        assertTrue(testList.getKursListe().isEmpty());
    }

    @Test
    void addKursZuListe() {
        KursListe testList = new KursListe();
        Kurs a = new Kurs();
        String name = "Kurs A";
        a.setName(name);
        testList.addKursZuListe(a);
        assertFalse(testList.getKursListe().isEmpty());
        assertEquals(a, testList.getKursListe().get(0));
        assertEquals(name, testList.getKursListe().get(0).getName());
    }

    @Test
    void getKursVonKursListe(){
        KursListe testIndex= new KursListe();
        Kurs b = new Kurs();
        Kurs c = new Kurs();
        Kurs d = new Kurs();
        Kurs e = new Kurs();
        Kurs f = new Kurs();
        assertEquals(b,c);

    }

}
