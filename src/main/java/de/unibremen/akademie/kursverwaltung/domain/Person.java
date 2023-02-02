package de.unibremen.akademie.kursverwaltung.domain;

import javafx.beans.property.SimpleStringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Person implements Externalizable {

    //static final long serialVersionUID = 3619323214958673905L;

    private SimpleStringProperty anrede;
    private SimpleStringProperty titel;
    private SimpleStringProperty nachname;
    private SimpleStringProperty vorname;
    private SimpleStringProperty strasse;
    private SimpleStringProperty plz;
    private SimpleStringProperty ort;
    private SimpleStringProperty email;
    private SimpleStringProperty telefon;


    //private ObservableList<Kurs> kursInteressiert = FXCollections.observableArrayList();
    //private ObservableList<Kurs> kursTeilnahme = FXCollections.observableArrayList();

    public Person() {
    }

    public Person(String anrede, String titel, String vorname, String nachname, String strasse, String plz, String ort, String email, String telefon) {
        if (!checkIsEmpty(vorname)) {
            throw new IllegalArgumentException("Der Vorname muss aus mindestens 2 Zeichen bestehen!");
        }
        if (!checkIsEmpty(nachname)) {
            throw new IllegalArgumentException("Der Nachname muss aus mindestens 2 Zeichen bestehen!");
        }
        if (!checkValidEmail(email)) {
            throw new IllegalArgumentException("Die Email-Adresse ist fehlerhaft!");
        }
        setAnrede(anrede);
        setTitel(titel);
        setVorname(vorname);
        setNachname(nachname);
        setStrasse(strasse);
        setPlz(plz);
        setOrt(ort);
        setEmail(email);
        setTelefon(telefon);
    }

    public void updatePerson(String anrede, String titel, String vorname, String nachname, String strasse, String plz, String ort, String email, String telefon) {

        if (!checkIsEmpty(vorname)) {
            throw new IllegalArgumentException("Der Vorname muss aus mindestens 2 Zeichen bestehen!");
            //vorname = getVorname();
        }
        if (!checkIsEmpty(nachname)) {
            throw new IllegalArgumentException("Der Nachname muss aus mindestens 2 Zeichen bestehen!");
            //nachname = getNachname();
        }
        if (!checkValidEmail(email)) {
            throw new IllegalArgumentException("Die Email-Adresse ist fehlerhaft!");
            //email = getEmail();
        }
        this.setAnrede(anrede);
        this.setTitel(titel);
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setStrasse(strasse);
        this.setPlz(plz);
        this.setOrt(ort);
        this.setEmail(email);
        this.setTelefon(telefon);
    }

    public String getAnrede() {
        return anrede.get();
    }

    public void setAnrede(String anrede) {
        this.anrede = new SimpleStringProperty(anrede);
    }

    public String getTitel() {
        return titel.get();
    }

    public void setTitel(String titel) {
        this.titel = new SimpleStringProperty(titel);
    }

    public String getNachname() {
        return nachname.get();
    }

    public String getVorname() {
        return vorname.get();
    }

    public void setVorname(String vorname) {
        if (checkIsEmpty(vorname)) {
            this.vorname = new SimpleStringProperty(vorname);
        } else {
            System.out.println("Vorname ist leer oder zu kurz!");
        }
    }

    public String getStrasse() {
        return strasse.get();
    }

    public void setStrasse(String strasse) {
        this.strasse = new SimpleStringProperty(strasse);
    }

    public String getPlz() {
        return plz.get();
    }

    public void setPlz(String plz) {
        this.plz = new SimpleStringProperty(plz);
    }

    public String getOrt() {
        return ort.get();
    }

    public void setOrt(String ort) {
        this.ort = new SimpleStringProperty(ort);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        if (checkValidEmail(email)) {
            this.email = new SimpleStringProperty(email);
        } else {
            System.out.println("Email ist falsch!");
        }
    }

    public String getTelefon() {
        return telefon.get();
    }



    public void setTelefon(String telefon) {
        this.telefon = new SimpleStringProperty(telefon);
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

    public void setNachname(String nachname) {
        if (checkIsEmpty(nachname)) {
            this.nachname = new SimpleStringProperty(nachname);
        } else {
            System.out.println("Nachname ist leer oder zu kurz!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(vorname.getValue(), person.vorname.getValue())
                && Objects.equals(nachname.getValue(), person.nachname.getValue())
                && Objects.equals(email.getValue(), person.email.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname.getValue(), nachname.getValue(), email.getValue());
    }

    @Override
    public String toString() {
        return "Person{" +
                "anrede='" + anrede + '\'' +
                "titel='" + titel + '\'' +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput stream) throws IOException {
        stream.writeUTF(getAnrede());
        stream.writeUTF(getTitel());
        stream.writeUTF(getVorname());
        stream.writeUTF(getNachname());
        stream.writeUTF(getStrasse());
        stream.writeUTF(getPlz());
        stream.writeUTF(getOrt());
        stream.writeUTF(getEmail());
        stream.writeUTF(getTelefon());
        //System.out.println(this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setAnrede(in.readUTF());
        setTitel(in.readUTF());
        setVorname(in.readUTF());
        setNachname(in.readUTF());
        setStrasse(in.readUTF());
        setPlz(in.readUTF());
        setOrt(in.readUTF());
        setEmail(in.readUTF());
        setTelefon(in.readUTF());
        //System.out.println(this);
    }


}
