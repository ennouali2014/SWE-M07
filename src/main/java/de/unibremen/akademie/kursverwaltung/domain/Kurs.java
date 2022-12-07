package de.unibremen.akademie.kursverwaltung.domain;

import java.util.ArrayList;
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
    private List<Person> interessentenListe = new ArrayList<>();
    private List<Person> teilnehmerListe=new ArrayList<>();

    private Kurs() {
    }
    public static Kurs addnewKurs(String name, int anzahlTage,int zyklus, Date startDatum,int maxTnZahl, int minTnZahl,
                                    double gebuehrBrutto,double mwstProzent,String kursBeschreibung,Person person){
        Kurs kurs = new Kurs();
        kurs.setKursBeschreibung(kursBeschreibung);
        kurs.setName(name);
        kurs.setAnzahlTage(anzahlTage);
        kurs.setZyklus(zyklus);
        kurs.setStartDatum(startDatum);
        kurs.setMaxTnZahl(maxTnZahl);
        kurs.setMinTnZahl(minTnZahl);
        kurs.setGebuehrBrutto(gebuehrBrutto);
        kurs.setMwstProzent(mwstProzent);
        kurs.setInteressentenListe(person);



        return null;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {

        if(name!=null) {
            this.name = name;
            return true;
        }
        return false;
    }

    public int getAnzahlTage() {
        return anzahlTage;
    }

    public boolean  setAnzahlTage(int anzahlTage) {
        if(anzahlTage >0){
            this.anzahlTage = anzahlTage;
            return true;
        }
        return false;

    }

    public int getZyklus() {
        return zyklus;
    }

    public boolean setZyklus(int zyklus) {
        if(zyklus>0 && zyklus<8) {
            this.zyklus = zyklus;
            return true;
        }
        return false;

    }

    public Date getStartDatum() {
        return startDatum;
    }

    public boolean setStartDatum(Date startDatum) {
        Date date = new Date();
        if(startDatum.before(date)){
            return false;
        }
        this.startDatum = startDatum;
        return true;
    }

    public Date getEndeDatum() {
        return endeDatum;
    }

    public void setEndeDatum(Date startDatum,int zyklus,int anzahlTage) {
        long dat=startDatum.getTime()+(Math.round(anzahlTage/zyklus)*7*86400000);
        this.endeDatum = new Date(dat);
    }

    public int getAktuelleTnZahl() {
        return aktuelleTnZahl;
    }

    public void setAktuelleTnZahl() {
        this.aktuelleTnZahl = this.teilnehmerListe.size();
    }

    public int getMinTnZahl() {
        return minTnZahl;
    }

    public boolean setMinTnZahl(int minTnZahl) {
        if (minTnZahl > 0 && minTnZahl < this.maxTnZahl) {
            this.minTnZahl = minTnZahl;
            return true;
        }
        return false;
    }

    public int getMaxTnZahl() {
        return maxTnZahl;
    }

    public boolean setMaxTnZahl(int maxTnZahl) {
        if (maxTnZahl > 0 && maxTnZahl > this.minTnZahl) {
            this.maxTnZahl = maxTnZahl;
            return true;
        }
        return false;

    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public boolean setFreiePlaetze() {
        if (this.maxTnZahl-this.aktuelleTnZahl>0) {
            this.freiePlaetze = this.maxTnZahl - this.aktuelleTnZahl;
            return true;
        }
        return false;
    }

    public double getGebuehrBrutto() {
        return gebuehrBrutto;
    }

    public boolean setGebuehrBrutto(double gebuehrBrutto) {
        if(gebuehrBrutto>0){
            this.gebuehrBrutto = gebuehrBrutto;
            return true;
        }
        return false;

    }

    public double getGebuehrNetto() {
        return gebuehrNetto;
    }

    public void setGebuehrNetto() {
        this.gebuehrNetto = this.gebuehrBrutto*((100-this.mwstProzent)/100);
    }

    public double getMwstEuro() {
        return mwstEuro;
    }

    public void setMwstEuro(double mwstEuro) {
        this.mwstEuro = this.gebuehrBrutto*(this.mwstProzent/100);
    }

    public double getMwstProzent() {
        return mwstProzent;
    }

    public boolean setMwstProzent(double mwstProzent) {
        if(mwstProzent>0){
            this.mwstProzent = mwstProzent;
            return true;
        }
        return false;

    }

    public String getKursBeschreibung() {
        return kursBeschreibung;
    }

    public void setKursBeschreibung(String kursBeschreibung) {
        this.kursBeschreibung = kursBeschreibung;
    }

    public List<Person> getInteressentenListe() {

        return interessentenListe;
    }

    public boolean setInteressentenListe(Person interessant) {
        if(interessant!=null){
            this.interessentenListe.add(interessant);
            return true;
        }
        return false;
    }

    public List<Person> getTeilnehmerListe() {
        return teilnehmerListe;
    }

    public boolean setTeilnehmerListe(Person teilnehmer) {
        if(teilnehmer!=null) {
            this.teilnehmerListe.add(teilnehmer);
            return true;
        }
        return false;
    }

}
