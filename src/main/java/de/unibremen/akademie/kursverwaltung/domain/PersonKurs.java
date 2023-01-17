package de.unibremen.akademie.kursverwaltung.domain;

import javafx.beans.property.SimpleBooleanProperty;

public class PersonKurs {

    private Person person;
    private Kurs kurs;
    private SimpleBooleanProperty teilnehmer;
    private SimpleBooleanProperty interessent;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public boolean isTeilnehmer() {
        return teilnehmer.get();
    }

    public void setTeilnehmer(boolean teilnehmer) {
        this.teilnehmer.set(teilnehmer);
    }

    public SimpleBooleanProperty teilnehmerProperty() {
        return teilnehmer;
    }

    public boolean isInteressent() {
        return interessent.get();
    }

    public void setInteressent(boolean interessent) {
        this.interessent.set(interessent);
    }

    public SimpleBooleanProperty interessentProperty() {
        return interessent;
    }
}
