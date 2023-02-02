package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonKursTest {
    KvModel testModel;

    @BeforeEach
    void beforeEach() {
        testModel = new KvModel();
    }

    @Test
    void getPerson() {

    }

    @Test
    void setPerson() {
        Person persontest = testModel.getPersonen().addNewPerson("Frau", "Dr. rer. nat.", "Ann", "Ahlers", "Aweg 12", "28195", "Bremen", "a@gmx.de", "+49 12 345 6789");
        Kurs kurstest = testModel.getKurse().addNewKurs("C", 10, 3, new Date(1898553600000L), 1, 10, 100, 19, "", "Aktiv");

        assertTrue(persontest.equals(new Person("Frau", "Dr. rer. nat.", "Ann", "Ahlers", "Aweg 12", "28195", "Bremen", "a@gmx.de","+49 12 345 6789" )));
        //assertFalse(persontest.equals(new Person("Frau", "Dr. rer. nat.", "Ann", "Ahlers", "Aweg 12", "28195", "Bremen", "a@gmx","+49 12 345 6789" )));
    }

    @Test
    void getKurs() {
    }

    @Test
    void setKurs() {
        Person persontest = testModel.getPersonen().addNewPerson("Frau", "Dr. rer. nat.", "Ann", "Ahlers", "Aweg 12", "28195", "Bremen", "a@gmx.de", "+49 12 345 6789");
        Kurs kurstest = testModel.getKurse().addNewKurs("C", 10, 3, new Date(1898553600000L), 1, 10, 100.0, 19.0, "C Beginner", "Aktiv");

        assertFalse(kurstest.equals(new Kurs("C", 10, 3, new Date(1898553600000L), 1, 10, 100.0, 19.0, "C Beginner", "Aktiv")));
        assertFalse(kurstest.equals(persontest));
    }

    @Test
    void isTeilnehmer() {
    }

    @Test
    void setTeilnehmer() {

    }

    @Test
    void writeExternal() {
    }

    @Test
    void readExternal() {
    }
}