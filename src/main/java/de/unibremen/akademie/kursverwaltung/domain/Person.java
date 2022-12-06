package de.unibremen.akademie.kursverwaltung.domain;

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
        if (email != null && email.length() >= 5) {
            this.email = email;
        }
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
