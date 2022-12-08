package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {

    @Test
    void testCheckIsEmpty() {
        assertTrue(Person.checkIsEmpty("  "));
        assertFalse(Person.checkIsEmpty(" "));
        assertFalse(Person.checkIsEmpty(""));
        assertFalse(Person.checkIsEmpty(null));
    }

    @Test
    void testCheckValidEmail() {
        assertTrue(Person.checkValidEmail("john-doe@hotmail.de"));
        assertFalse(Person.checkValidEmail("jan@hotmail..international"));
        assertTrue(Person.checkValidEmail("jane_doe@hotmail.mail.international"));
        assertFalse(Person.checkValidEmail("@hotmail"));
        assertFalse(Person.checkValidEmail("ö@hotmail.de"));
        assertFalse(Person.checkValidEmail("@hotmail.d"));
        assertFalse(Person.checkValidEmail("m@.de"));
        assertTrue(Person.checkValidEmail("m@d.de"));
        assertFalse(Person.checkValidEmail("m@d..de"));
    }
}