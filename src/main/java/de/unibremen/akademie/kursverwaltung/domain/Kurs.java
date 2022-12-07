package de.unibremen.akademie.kursverwaltung.domain;

import java.util.Date;
import java.util.List;

public class Kurs {

    private String name;
    private int anzahlTage;
    private int zyklus;
    private Date startDatum;
    private Date endeDatum;
    private int aktuelleTnZahl;
    private int minTnZahl;
    private int maxTnZahl;
    private int freiePlaetze;
    private double gebuehrBrutto;
    private double gebuehrNetto;
    private double mwstEuro;
    private double mwstProzent;
    private String kursBeschreibung;
    private List<Person> interessentenListe;
    private List<Person> teilnehmerListe;


    public Kurs(String name, int anzahlTage, int zyklus, Date startDatum, int minTnZahl, int maxTnZahl, double gebuehrBrutto, double mwstProzent, String kursBeschreibung) {
        this.name = name;
        this.anzahlTage = anzahlTage;
        this.zyklus = zyklus;
        this.startDatum = startDatum;
        this.minTnZahl = minTnZahl;
        this.maxTnZahl = maxTnZahl;
        this.gebuehrBrutto = gebuehrBrutto;
        this.mwstProzent = mwstProzent;
        this.kursBeschreibung = kursBeschreibung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahlTage() {
        return anzahlTage;
    }

    public void setAnzahlTage(int anzahlTage) {
        this.anzahlTage = anzahlTage;
    }

    public int getZyklus() {
        return zyklus;
    }

    public void setZyklus(int zyklus) {
        this.zyklus = zyklus;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEndeDatum() {
        return endeDatum;
    }

    public void setEndeDatum(Date endeDatum) {
        this.endeDatum = endeDatum;
    }

    public int getAktuelleTnZahl() {
        return aktuelleTnZahl;
    }

    public void setAktuelleTnZahl(int aktuelleTnZahl) {
        this.aktuelleTnZahl = aktuelleTnZahl;
    }

    public int getMinTnZahl() {
        return minTnZahl;
    }

    public void setMinTnZahl(int minTnZahl) {
        this.minTnZahl = minTnZahl;
    }

    public int getMaxTnZahl() {
        return maxTnZahl;
    }

    public void setMaxTnZahl(int maxTnZahl) {
        this.maxTnZahl = maxTnZahl;
    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(int freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }

    public double getGebuehrBrutto() {
        return gebuehrBrutto;
    }

    public void setGebuehrBrutto(double gebuehrBrutto) {
        this.gebuehrBrutto = gebuehrBrutto;
    }

    public double getGebuehrNetto() {
        return gebuehrNetto;
    }

    public void setGebuehrNetto(double gebuehrNetto) {
        this.gebuehrNetto = gebuehrNetto;
    }

    public double getMwstEuro() {
        return mwstEuro;
    }

    public void setMwstEuro(double mwstEuro) {
        this.mwstEuro = mwstEuro;
    }

    public double getMwstProzent() {
        return mwstProzent;
    }

    public void setMwstProzent(double mwstProzent) {
        this.mwstProzent = mwstProzent;
    }

    public String getKursBeschreibung() {
        return kursBeschreibung;
    }

    public void setKursBeschreibung(String kursBeschreibung) {
        this.kursBeschreibung = kursBeschreibung;
    }
}
