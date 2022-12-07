package de.unibremen.akademie.kursverwaltung.domain;

import java.util.List;
import java.util.Objects;

public class Person {

    private Anrede anrede;
    private String titel;
    private String name;
    private String vorname;
    private String strasse;
    private String plz;
    private String ort;
    private String email;
    private String telefon;
    private List<Kurs> kursInteressent;
    private List<Kurs> kursTeilnahme;


    public Person() {
    }

    public Anrede getAnrede() {
        return anrede;
    }

    public void setAnrede(Anrede anrede) {
        this.anrede = anrede;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() >= 2) {
            this.name = name;
        }
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        if (vorname != null && vorname.length() >= 2) {
            this.vorname = vorname;
        }
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        if (strasse != null && strasse.length() >= 5) {
            this.strasse = strasse;
        }
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        if (plz != null && plz.length() >= 5) {
            this.plz = plz;
        }
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        if (ort != null && ort.length() >= 2) {
            this.ort = ort;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.equals("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")) {
            this.email = email;
        }
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        if (telefon.equals("0\\d{2,4}\\s?[\\-]?\\s?\\d{3,4}\\s?\\d{3,4}")) {
            this.telefon = telefon;
        }
    }

    public List<Kurs> getKursInteressent() {
        return kursInteressent;
    }

    public void setKursInteressent(List<Kurs> kursInteressent) {
        this.kursInteressent = kursInteressent;
    }

    public List<Kurs> getKursTeilnahme() {
        return kursTeilnahme;
    }

    public void setKursTeilnahme(List<Kurs> kursTeilnahme) {
        this.kursTeilnahme = kursTeilnahme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(vorname, person.vorname) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vorname, email);
    }
}
