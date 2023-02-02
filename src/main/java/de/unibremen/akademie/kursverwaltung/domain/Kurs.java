package de.unibremen.akademie.kursverwaltung.domain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kurs implements Externalizable {
    private SimpleStringProperty name;
    private SimpleIntegerProperty anzahlTage;
    private SimpleIntegerProperty zyklus;
    private Date startDatum;
    private Date endeDatum;
    private SimpleIntegerProperty aktuelleTnZahl;
    private SimpleIntegerProperty minTnZahl;
    private SimpleIntegerProperty maxTnZahl;
    private SimpleIntegerProperty freiePlaetze;
    private SimpleDoubleProperty gebuehrBrutto;
    private SimpleDoubleProperty gebuehrNetto;
    private SimpleDoubleProperty mwstEuro;
    private SimpleDoubleProperty mwstProzent;
    private SimpleStringProperty kursBeschreibung;
    private SimpleStringProperty status;
    private final List<Person> interessentenListe = new ArrayList<>();
    private final List<Person> teilnehmerListe = new ArrayList<>();
    private SimpleStringProperty displaystartDate;
    private SimpleStringProperty displayEndeDate;

    public Kurs() {
        this.name = new SimpleStringProperty();
    }

    public Kurs(String name, int anzahlTage, int zyklus, Date startDatum, int minTnZahl, int maxTnZahl,
                double gebuehrBrutto, double mwstProzent, String kursBeschreibung, String statusSTR) {
        this.name = new SimpleStringProperty();
        if (!setName(name)) {
            throw new IllegalArgumentException("Der Kurs-Name ist leer!");
        }
        if (!setAnzahlTage(anzahlTage)) {
            throw new IllegalArgumentException("Der Kurs muss mindestens 1 Tag dauern!");
        }
        if (!setZyklus(zyklus)) {
            throw new IllegalArgumentException("Bitte einen Zyklus angeben!");
        }
        if (!setStartDatum(startDatum)) {
            throw new IllegalArgumentException("Wann soll der Kurs starten?");
        }
        if (!setMinTnZahl(minTnZahl)) {
            throw new IllegalArgumentException("Mindestens 1 Teilnehmer sollte schon sein!");
        }
        if (!setMaxTnZahl(maxTnZahl)) {
            throw new IllegalArgumentException("Die maximale Anzahl der Teilnehmer muss mindestens so gross sein \nwie die minimale Anzahl der Teilnehmer!");
        }
        if (!setGebuehrBrutto(gebuehrBrutto)) {
            throw new IllegalArgumentException("Die Gebühr sollte mindestens 1,- symbolischen Euro betragen!");
        }
        if (!setMwstProzent(mwstProzent)) {
            throw new IllegalArgumentException("Bitte einen MwSt-Satz angeben!");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        setDisplaystartDate(dateFormat.format(startDatum));
        setKursBeschreibung(kursBeschreibung);
        setEndeDatum();
        setDisplayEndeDate(dateFormat.format(getEndeDatum()));
        setGebuehrNetto();
        setMwstEuro();
        setAktuelleTnZahl();
        if (!setFreiePlaetze()) {
            throw new IllegalArgumentException("Leider sind alles Plätze belegt!");
        }
        setStatus(statusSTR);
    }

    public String getName() {
        return name.get();
    }

    public boolean setName(String name) {
        if (name != null && name.length() > 0) {
            if (this.name == null) {
                this.name = new SimpleStringProperty(name);
            } else {
                this.name.set(name);
            }
            return true;
        }
        return false;
    }

    public int getAnzahlTage() {
        return anzahlTage.get();
    }

    public boolean setAnzahlTage(int anzahlTage) {
        if (anzahlTage > 0) {
            if (this.anzahlTage == null) {
                this.anzahlTage = new SimpleIntegerProperty(anzahlTage);
            } else {
                this.anzahlTage.set(anzahlTage);
            }
            return true;
        }
        return false;
    }

    public int getZyklus() {
        return zyklus.get();
    }

    public boolean setZyklus(int zyklus) {
        if (zyklus > 0 && zyklus < 8) {
            if (this.zyklus == null) {
                this.zyklus = new SimpleIntegerProperty(zyklus);
            } else {
                this.zyklus.set(zyklus);
            }
            return true;
        }
        return false;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public boolean setStartDatum(Date startDatum) {
        Date date = new Date();
        if (startDatum.before(date)) {
            return false;
        }
        this.startDatum = startDatum;
        return true;
    }

    public Date getEndeDatum() {
        return endeDatum;
    }

    public void setEndeDatum() {
        long dat = startDatum.getTime() + ((Math.round((float) anzahlTage.get() / zyklus.get())) * 7 * 86400000L);
        this.endeDatum = new Date(dat);

    }
    public void setEndeDatum(Date endeDatum){
        this.endeDatum=endeDatum;
    }

    public int getAktuelleTnZahl() {
        return aktuelleTnZahl.get();
    }

    public void setAktuelleTnZahl() {
        if (this.aktuelleTnZahl == null) {
            this.aktuelleTnZahl = new SimpleIntegerProperty(this.teilnehmerListe.size());
        } else {
            this.aktuelleTnZahl.set(this.teilnehmerListe.size());
        }

    }
    public void setAktuelleTnZahl(int aktuelleTnZahl){
        this.aktuelleTnZahl=new SimpleIntegerProperty(aktuelleTnZahl);
    }

    public int getMinTnZahl() {
        if (minTnZahl != null) {
            return minTnZahl.get();
        } else {
            return 0;
        }
    }

    public boolean setMinTnZahl(int minTnZahl) {
        if (minTnZahl > 0) {
            if (this.minTnZahl == null) {
                this.minTnZahl = new SimpleIntegerProperty(minTnZahl);
            } else {
                this.minTnZahl.set(minTnZahl);
            }

            return true;
        }
        return false;
    }

    public int getMaxTnZahl() {
        return maxTnZahl.get();
    }

    public boolean setMaxTnZahl(int maxTnZahl) {
        if (maxTnZahl >= this.minTnZahl.get()) {
            if (this.maxTnZahl == null) {
                this.maxTnZahl = new SimpleIntegerProperty(maxTnZahl);
            } else {
                this.maxTnZahl.set(maxTnZahl);
            }
            return true;
        }
        return false;
    }

    public int getFreiePlaetze() {
        return freiePlaetze.get();
    }

    public boolean setFreiePlaetze() {
        if (this.maxTnZahl.get() - this.aktuelleTnZahl.get() > 0) {
            if (this.freiePlaetze == null) {
                this.freiePlaetze = new SimpleIntegerProperty(this.maxTnZahl.get() - this.aktuelleTnZahl.get());
            } else {
                this.freiePlaetze.set(this.maxTnZahl.get() - this.aktuelleTnZahl.get());
            }

            return true;
        }
        return false;
    }
    public void setFreiePlaetze(int freiePlaetze){
        this.freiePlaetze=new SimpleIntegerProperty(freiePlaetze);
    }

    public double getGebuehrBrutto() {
        return gebuehrBrutto.get();
    }

    public boolean setGebuehrBrutto(double gebuehrBrutto) {
        if (gebuehrBrutto > 0) {
            if (this.gebuehrBrutto == null) {
                this.gebuehrBrutto = new SimpleDoubleProperty(gebuehrBrutto);
            } else {
                this.gebuehrBrutto.set(gebuehrBrutto);
            }

            return true;
        }
        return false;

    }

    public double getGebuehrNetto() {
        return gebuehrNetto.get();
    }

    public void setGebuehrNetto() {
        if (this.gebuehrNetto == null) {
            this.gebuehrNetto = new SimpleDoubleProperty(Math.round((gebuehrBrutto.get() * ((100 - mwstProzent.get()) / 100)) * 100.0) / 100.0);
        } else {
            this.gebuehrNetto.set(Math.round((gebuehrBrutto.get() * ((100 - mwstProzent.get()) / 100)) * 100.0) / 100.0);
        }
    }

    public void setGebuehrNetto(double gebuehrNetto){
        this.gebuehrNetto=new SimpleDoubleProperty(gebuehrNetto);
    }

    public double getMwstEuro() {
        return mwstEuro.get();
    }

    public void setMwstEuro() {
        if (this.mwstEuro == null) {
            this.mwstEuro = new SimpleDoubleProperty(Math.round((gebuehrBrutto.get() * (mwstProzent.get() / 100)) * 100.0) / 100.0);
        } else {
            this.mwstEuro.set(Math.round((gebuehrBrutto.get() * (mwstProzent.get() / 100)) * 100.0) / 100.0);
        }
    }
    public void setMwstEuro(double mwstEuro){
        this.mwstEuro=new SimpleDoubleProperty(mwstEuro);
    }

    public double getMwstProzent() {
        return mwstProzent.get();
    }

    public boolean setMwstProzent(double mwstProzent) {
        if (mwstProzent >= 0) {
            if (this.mwstProzent == null) {
                this.mwstProzent = new SimpleDoubleProperty(mwstProzent);
            } else {
                this.mwstProzent.set(mwstProzent);
            }

            return true;
        }
        return false;
    }

    public String getKursBeschreibung() {
        return kursBeschreibung.get();
    }

    public boolean setKursBeschreibung(String kursBeschreibung) {
        if (kursBeschreibung != null) {
            if (this.kursBeschreibung == null) {
                this.kursBeschreibung = new SimpleStringProperty(kursBeschreibung);
            } else {
                this.kursBeschreibung.set(kursBeschreibung);
            }
            return true;
        }
        return false;
    }

    public List<Person> getInteressentenListe() {
        return interessentenListe;
    }

    public boolean setInteressentenListe(Person interessant) {
        if (interessant != null) {
            this.interessentenListe.add(interessant);
            return true;
        }
        return false;
    }

    public List<Person> getTeilnehmerListe() {
        return teilnehmerListe;
    }

    public boolean setTeilnehmerListe(Person teilnehmer) {
        if (teilnehmer != null) {
            this.teilnehmerListe.add(teilnehmer);
            return true;
        }
        return false;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getName());
        out.writeInt(getAnzahlTage());
        out.writeInt(getZyklus());
        out.writeObject(getStartDatum());
        out.writeInt(getMinTnZahl());
        out.writeInt(getMaxTnZahl());
        out.writeDouble(getGebuehrBrutto());
        out.writeDouble(getMwstProzent());
        out.writeUTF(getKursBeschreibung());
        out.writeObject(getEndeDatum());
        out.writeUTF(getStatus());
        out.writeDouble(getGebuehrNetto());
        out.writeDouble(getMwstEuro());
        out.writeInt(getFreiePlaetze());
        out.writeInt(getAktuelleTnZahl());
        out.writeUTF(getDisplaystartDate());
        out.writeUTF(getDisplayEndeDate());
        //System.out.println(this);
    }


    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //set(stream.readBoolean());
        setName(in.readUTF());
        setAnzahlTage(in.readInt());
        setZyklus(in.readInt());
        setStartDatum((Date) in.readObject());
        setMinTnZahl(in.readInt());
        setMaxTnZahl(in.readInt());
        setGebuehrBrutto(in.readDouble());
        setMwstProzent(in.readDouble());
        setKursBeschreibung(in.readUTF());
        setEndeDatum((Date) in.readObject());
        setStatus(in.readUTF());
        setGebuehrNetto(in.readDouble());
        setMwstEuro(in.readDouble());
        setFreiePlaetze(in.readInt());
        setAktuelleTnZahl(in.readInt());
        setDisplaystartDate(in.readUTF());
        setDisplayEndeDate(in.readUTF());

        //System.out.println(this);
    }
    @Override
    public String toString() {
        return "Kurs{" +
                "name=" + name.get() +
                ", anzahlTage=" + anzahlTage.get() +
                ", zyklus=" + zyklus.get() +
                ", startDatum=" + startDatum.getTime() +
                ", minTnZahl=" + minTnZahl.get() +
                ", maxTnZahl=" + maxTnZahl.get() +
                ", gebuehrBrutto=" + gebuehrBrutto.get() +
                ", mwstProzent=" + mwstProzent.get() +
                ", kursBeschreibung=" + kursBeschreibung.get() +
                ", endeDatum=" + endeDatum.getTime() +
                ", status=" + status.get() +
                ", gebuehrNetto=" + gebuehrNetto.get() +
                ", mwstEuro=" + mwstEuro.get() +
                ", freiePlaetze=" + freiePlaetze.get() +
                ", aktuelleTnZahl=" + aktuelleTnZahl.get() +

                '}';
    }

    public String getDisplaystartDate() {
        return displaystartDate.get();
    }

    public void setDisplaystartDate(String date) {
        displaystartDate = new SimpleStringProperty(date);
    }

    public String getDisplayEndeDate() {
        return displayEndeDate.get();
    }

    public void setDisplayEndeDate(String date) {
        displayEndeDate = new SimpleStringProperty(date);
    }

}
