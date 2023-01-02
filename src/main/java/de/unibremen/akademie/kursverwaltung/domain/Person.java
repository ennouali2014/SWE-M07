package de.unibremen.akademie.kursverwaltung.domain;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.Objects;

public class Person {

    private Anrede anrede;
    private SimpleStringProperty titel;
    private SimpleStringProperty name;
    private SimpleStringProperty vorname;
    private SimpleStringProperty strasse;
    private SimpleStringProperty plz;
    private SimpleStringProperty ort;
    private SimpleStringProperty email;
    private SimpleStringProperty telefon;
    private List<Kurs> kursInteressiert;
    private List<Kurs> kursTeilnahme;


    public Person() {
    }


    public Anrede getAnrede() {
        return anrede;
    }

    public void setAnrede(Anrede anrede) {
        this.anrede = anrede;
    }

    public SimpleStringProperty getTitel() {
        return titel;
    }

    public void setTitel(SimpleStringProperty titel) {
        this.titel = titel;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        if (checkIsEmpty(name)) {
            this.name = name;
        }
    }

    public SimpleStringProperty getVorname() {
        return vorname;
    }

    public void setVorname(SimpleStringProperty vorname) {
        if (checkIsEmpty(vorname)) {
            this.vorname = vorname;
        }
    }

    public SimpleStringProperty getStrasse() {
        return strasse;
    }

    public void setStrasse(SimpleStringProperty strasse) {
        this.strasse = strasse;
    }

    public SimpleStringProperty getPlz() {
        return plz;
    }

    public void setPlz(SimpleStringProperty plz) {
        this.plz = plz;
    }

    public SimpleStringProperty getOrt() {
        return ort;
    }

    public void setOrt(SimpleStringProperty ort) {
        this.ort = ort;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public void setEmail(SimpleStringProperty email) {
        if (checkValidEmail(email)) {
            this.email = email;
        } else {
            System.out.println("Email ist falsch!");
        }
    }

    public SimpleStringProperty getTelefon() {
        return telefon;
    }

    public void setTelefon(SimpleStringProperty telefon) {
        this.telefon = telefon;
    }

    public List<Kurs> getKursInteressiert() {
        return kursInteressiert;
    }

    public void setKursInteressiert(List<Kurs> kursInteressiert) {
        this.kursInteressiert = kursInteressiert;
    }

    public List<Kurs> getKursTeilnahme() {
        return kursTeilnahme;
    }

    public void setKursTeilnahme(List<Kurs> kursTeilnahme) {
        this.kursTeilnahme = kursTeilnahme;
    }




    public static boolean checkIsEmpty(String wert) {
        return wert != null && wert.length() >= 2;
    }

    public static boolean checkValidEmail(String email) {
        String pattern = ("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
