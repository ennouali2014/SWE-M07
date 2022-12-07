package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {

    @Test
    void testCheckValidEmail() {
        assertTrue(Person.checkValidEmail("mdabour@hotmail.de"));
        assertFalse(Person.checkValidEmail("mdabour@hotmail..international"));
        assertTrue(Person.checkValidEmail("mdabour@hotmail.mail.international"));
        assertFalse(Person.checkValidEmail("@hotmail"));
        assertFalse(Person.checkValidEmail("@hotmail.de"));
        assertFalse(Person.checkValidEmail("@hotmail.d"));
        assertFalse(Person.checkValidEmail("m@.de"));
        assertTrue(Person.checkValidEmail("m@d.de"));
        assertFalse(Person.checkValidEmail("m@d..de"));


    }



}