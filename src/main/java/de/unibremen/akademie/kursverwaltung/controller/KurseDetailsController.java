package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Kursverwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class KurseDetailsController {

    public Tab ContentKurseDetails;
    private Label name_lbl;
    private Label anzahlTage_lbl;
    private Label zyklus_lbl;
    private Label startDatum_lbl;
    private Label endeDatum_lbl;
    private Label minTnZahl_lbl;
    private Label maxTnZahl_lbl;
    private Label aktuelleTnZahl_lbl;
    private Label freiePlaetze_lbl;
    private Label gebuehrBrutto_lbl;
    private Label gebuehrNetto_lbl;
    private Label mtwsEuro_lbl;
    private Label mtwsProzent_lbl;
    private Label kursBeschreibung_lbl;
    @FXML
    private TextField kursname;
    @FXML
    private TextField anzahlTage;
    @FXML
    private TextField zyklus;
    @FXML
    private DatePicker startDatum;
    @FXML
    private DatePicker endeDatum;
    @FXML
    private TextField minTnZahl;
    @FXML
    private TextField maxTnZahl;
    @FXML
    private TextField aktuelleTnZahl;
    @FXML
    private TextField freiePlaetze;
    @FXML
    private TextField gebuehrBrutto;
    @FXML
    private TextField gebuehrNetto;
    @FXML
    private TextField mtwsEuro;
    @FXML
    private TextField mtwsProzent;
    @FXML
    private TextArea kursBeschreibung;



    public void apply(ActionEvent actionEvent) {
        //do it start datum ist nur ein beispie. man muss datepicker anwenden recherchieren
        //do it endeDatum, aktuelleTeilnehmeranzahl, freiePlätze, mwst und gebühr netto muss werden aufgeruft.
        //maxTn und minZn anders
        String name = kursname.getText();
        int anzahl = Integer.parseInt(anzahlTage.getText());
        int zykls = Integer.parseInt(zyklus.getText());
        LocalDate localDate = startDatum.getValue();
        Date startDate = Date.from(localDate.atStartOfDay(ZoneId.of("CET")).toInstant());
        int minTn = Integer.parseInt(minTnZahl.getText());
        int maxTn = Integer.parseInt(maxTnZahl.getText());
        double gebuhrB = Double.parseDouble(gebuehrBrutto.getText());
        double mwstPro = Double.parseDouble(mtwsProzent.getText());
        String kursBesch = kursBeschreibung.getText();


        Kurs kurs = Kursverwaltung.model.addnewKurs(name, anzahl, zykls, startDate, minTn, maxTn, gebuhrB, mwstPro, kursBesch);

        LocalDate datetolocal = LocalDate.ofInstant(kurs.getEndeDatum().toInstant(), ZoneId.of("CET"));
        endeDatum.setValue(datetolocal);
        aktuelleTnZahl.setText(String.valueOf(kurs.getAktuelleTnZahl()));
        freiePlaetze.setText(String.valueOf(kurs.getFreiePlaetze()));
        mtwsEuro.setText(String.valueOf(kurs.getMwstEuro()));
        gebuehrNetto.setText(String.valueOf(kurs.getGebuehrNetto()));

        for(Tab tabPaneKursListe :ContentKurseDetails.getTabPane().getTabs()){
            if(tabPaneKursListe.getText().equals("Kurse-Liste")){
                tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
            }

        }

    }

    /*public void abbrechen(ActionEvent actionEvent) {

        kursname.clear();
        kursname.clear();
        anzahlTage.clear();
        zyklus.clear();
        startDatum.setValue(null);
        minTnZahl.clear();
        maxTnZahl.clear();
        gebuehrBrutto.clear();
        mtwsProzent.clear();
        kursBeschreibung.clear();

    }*/



    public void teilnehmerlist(ActionEvent actionEvent) {
    }

    public void interessentenlist(ActionEvent actionEvent) {
    }

    public void onDatePickerAction(ActionEvent actionEvent) {
    }
}
