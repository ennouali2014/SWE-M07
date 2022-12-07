package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class PersonTest {

    @Test
    void testCheckValidEmail() {
        assertEquals(true, Person.checkValidEmail("mdabour@hotmail.de"));
        assertEquals(false, Person.checkValidEmail("mdabour@hotmail..international"));
        assertEquals(true, Person.checkValidEmail("mdabour@hotmail.mail.international"));
        assertEquals(false, Person.checkValidEmail("@hotmail"));


    }



}