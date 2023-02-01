package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testCheckIsEmpty() {
        assertTrue(Person.checkIsEmpty("  "));
        assertTrue(Person.checkIsEmpty("12"));
        assertTrue(Person.checkIsEmpty("--"));
        assertTrue(Person.checkIsEmpty("aa"));
        assertFalse(Person.checkIsEmpty(" "));
        assertFalse(Person.checkIsEmpty(""));
        assertFalse(Person.checkIsEmpty(null));
        assertFalse(Person.checkIsEmpty("1"));
    }

    @Test
    void testCheckValidEmail() {
        assertTrue(Person.checkValidEmail("john-doe@hotmail.de"));
        assertFalse(Person.checkValidEmail("jan@hotmail..international"));
        assertTrue(Person.checkValidEmail("jane_doe@hotmail.mail.international"));
        assertFalse(Person.checkValidEmail("@hotmail"));
        assertFalse(Person.checkValidEmail("ö@hotmail.de"));
        assertTrue(Person.checkValidEmail("a&o@hotmail.de"));
        assertFalse(Person.checkValidEmail("@hotmail.d"));
        assertFalse(Person.checkValidEmail("m@.de"));
        assertTrue(Person.checkValidEmail("m@d.de"));
        assertFalse(Person.checkValidEmail("m@d..de"));
    }

    @Test
    void testCheckValidEmailLaenge() {
        /*
       Jede E-Mail-Adresse besteht aus zwei Teilen, die durch das Symbol @ verbunden sind. Der Name des Servers bildet den „hinteren“ Teil der Mail-Adresse, englisch als „domain-part“ bezeichnet. Vor dem @ steht der individuelle Name des Benutzers, der im Englischen als „local-part“ bezeichnet wird.
Indem Sie sich für einen E-Mail-Provider entscheiden, legen Sie den „hinteren“ Teil der Mail-Adresse fest. Wenn Sie sich beispielsweise für den Anbieter WEB.DE entschieden haben, endet Ihre E-Mail-Adresse auf „@web.de“. Den individuellen Teil der Adresse können Sie frei wählen, sofern Ihr Wunschname noch nicht vergeben ist.
Der Internet-Standard RFC 2822 legt fest, aus welchen Buchstaben, Zahlen und Sonderzeichen die Adresse bestehen darf: A-Z, a-z, 0-9 sowie .!#$%&'*+-/=?^_`{|}~. Andere Zeichen, wie zum Beispiel Leerzeichen, dürfen benutzt werden, wenn local-part in doppelte Anführungsstriche eingeschlossen wird. Alle Zeichen oberhalb des ASCII-Codes 127, also auch Umlaute und „ß“, sind generell verboten. Am Anfang und Ende der Zeichenkette darf sich kein Punkt befinden. Die Adresse darf maximal 254 Zeichen lang sein.
Es sind folgende Besonderheiten zu beachten:
    Von der Verwendung von Großbuchstaben wird abgeraten. Offiziell sind sie zwar erlaubt, aber ihre Benutzung ist riskant. Manche E-Mail-Provider können damit nicht umgehen und es kann Probleme mit Web-Formularen geben.
    Seit 2010 sind nichtlateinische Zeichen erlaubt, also auch Umlaute und „ß“. Obwohl die meisten Provider die deutschen Umlaute akzeptieren, wird davon abgeraten. Vielleicht ist einer der Server auf dem Transportweg noch nicht umgestellt und weist die Mail zurück.
    */
        assertFalse(Person.checkValidEmail("ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggm@d.de"));
    }

    @Test
    void addNewPersonTest() {
//        Person person = Person.addNewPerson("", "", "Al", "Bundy", "", "", "", "a@mail.de", "");
//        assertEquals(1, KvModel.personList.size());
//        Person.addNewPerson("", "", "Al", "Bundy", "", "", "", "a@mail.de", "");
//        assertEquals(2, KvModel.personList.size());
        //Person.addNewPerson("", "", "", "B", "", "", "", "a@mail.", "");
        //assertEquals(2, KvModel.personList.size());
    }
}