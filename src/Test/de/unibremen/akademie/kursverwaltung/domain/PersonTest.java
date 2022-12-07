package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class PersonTest {

    @Test
    void testCheckValidEmail() {
        boolean val;
        String mail = "mdabour@hotmail.de";
        Person person = new Person();
        val = person.checkValidEmail(mail);
        assertEquals(mail, val);


    }


}