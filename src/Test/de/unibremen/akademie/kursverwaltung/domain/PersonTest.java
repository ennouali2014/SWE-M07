package de.unibremen.akademie.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;
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
        assertTrue(Person.checkValidEmail("ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggm@d.de"));
    }

    @Test
    public void testAddNewPerson() {
        Person person = Person.addNewPerson("Herr", "Dr.", "John", "Doe", "Street 1", "12345", "City", "john.doe@email.com", "1234567890");
        assertEquals("Herr", person.getAnrede());
        assertEquals("Dr.", person.getTitel());
        assertEquals("John", person.getVorname());
        assertEquals("Doe", person.getNachname());
        assertEquals("Street 1", person.getStrasse());
        assertEquals("12345", person.getPlz());
        assertEquals("City", person.getOrt());
        assertEquals("john.doe@email.com", person.getEmail());
        assertEquals("1234567890", person.getTelefon());
        assertTrue(kvModel.getPersonen().getPersonenListe().contains(person));
    }

    @Test
    public void testAddNewPersonInvalidVorname() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person.addNewPerson("Herr", "Dr.", "", "Doe", "Street 1", "12345",
                    "City", "john.doe@email.com", "1234567890");
        });
    }

    @Test
    public void testAddNewPersonInvalidNachname() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person.addNewPerson("Herr", "Dr.", "John", "", "Street 1", "12345",
                    "City", "john.doe@email.com", "1234567890");
        });
    }

    @Test
    public void testAddNewPersonInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person.addNewPerson("Herr", "Dr.", "John", "Doe", "Street 1",
                    "12345", "City", "johndoeemail.com", "1234567890");
        });
    }


    @Test
    public void testUpdatePerson() {
        Person person = new Person();

        //Test case 1: Test with valid input
        person.updatePerson("Herr", "Dr.", "John", "Doe", "Street 1", "12345", "City", "john.doe@example.com", "123456");
        assertEquals("Herr", person.getAnrede());
        assertEquals("Dr.", person.getTitel());
        assertEquals("John", person.getVorname());
        assertEquals("Doe", person.getNachname());
        assertEquals("Street 1", person.getStrasse());
        assertEquals("12345", person.getPlz());
        assertEquals("City", person.getOrt());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("123456", person.getTelefon());

        //Test case 2: Test with short first name
        try {
            person.updatePerson("Herr", "Dr.", "J", "Doe", "Street 1", "12345", "City", "john.doe@example.com", "123456");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Der Vorname muss aus mindestens 2 Zeichen bestehen!", e.getMessage());
        }

        //Test case 3: Test with short last name
        try {
            person.updatePerson("Herr", "Dr.", "John", "D", "Street 1", "12345", "City", "john.doe@example.com", "123456");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Der Nachname muss aus mindestens 2 Zeichen bestehen!", e.getMessage());
        }

        //Test case 4: Test with invalid email
        try {
            person.updatePerson("Herr", "Dr.", "John", "Doe", "Street 1", "12345", "City", "johndoe.com", "123456");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Die Email-Adresse ist fehlerhaft!", e.getMessage());
        }
    }

}