package de.unibremen.akademie.kursverwaltung.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kursverwaltung {
    private List<Person> personList=new ArrayList<>();
    private List<Kurs> kursList=new ArrayList<>();

    public String addnewKurs( String name, int anzahlTage, int zyklus, Date startDatum, int maxTnZahl, int minTnZahl,
                                    double gebuehrBrutto, double mwstProzent, String kursBeschreibung) {
        Kurs kurs = new Kurs();
        kurs.setKursBeschreibung(kursBeschreibung);
        if(kurs.setName(name)==false){return "Name is Require";}
        if(kurs.setAnzahlTage(anzahlTage)==false){return "minimum teilnahme ist falsch";}
        if(kurs.setZyklus(zyklus)==false){return "zyklus is Require";}
        if(kurs.setStartDatum(startDatum)==false){return " Start Date is Require";}
        if(kurs.setMaxTnZahl(maxTnZahl)==false){return " Max anzahl darf nicht weniger als Min anzahl der Teilnehmer";};
        if(kurs.setMinTnZahl(minTnZahl)==false){return "minimum teilnahme ist falsch";}
        if(kurs.setGebuehrBrutto(gebuehrBrutto)==false){return "gebuhr Brutto ist falssch";}
        if(kurs.setMwstProzent(mwstProzent)==false){return "prozent MWST is Require";}
        kurs.setEndeDatum(startDatum, zyklus, anzahlTage);
        kurs.setGebuehrNetto(gebuehrBrutto, mwstProzent);
        kurs.setMwstEuro(mwstProzent, gebuehrBrutto);
        kurs.setAktuelleTnZahl();
        kursList.add(kurs);
        return "OK";
    }
}
