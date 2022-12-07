package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class PersonTest {

    @Test
    void testCheckValidEmail() {
        boolean mail = true;
        boolean val;
        String regex = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
        Person person = new Person();
        val = person.checkValidEmail(regex);
        assertEquals(mail, val);


    }


}