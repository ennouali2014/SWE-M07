package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kursverwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Date;

public class KurseDetailsController {

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


    public void speichern(ActionEvent actionEvent) {
        //do it start datum ist nur ein beispie. man muss datepicker anwenden recherchieren
        //do it endeDatum, aktuelleTeilnehmeranzahl, freiePlätze, mwst und gebühr netto muss werden aufgeruft.
        Kursverwaltung kursverwaltung = new Kursverwaltung();
        String name = kursname.getText();
        int anzahl = Integer.parseInt(anzahlTage.getText());
        int zykls = Integer.parseInt(zyklus.getText());
        int minTn = Integer.parseInt(minTnZahl.getText());
        int maxTn = Integer.parseInt(maxTnZahl.getText());
        double gebuhrB = Double.parseDouble(gebuehrBrutto.getText());
        double mwstPro = Double.parseDouble(mtwsProzent.getText());
        String kursBesch = kursBeschreibung.getText();

        kursverwaltung.addnewKurs(name, anzahl, zykls, new Date(), minTn, maxTn, gebuhrB, mwstPro, kursBesch);
    }

    public void abbrechen(ActionEvent actionEvent) {
    }

    public void teilnehmerlist(ActionEvent actionEvent) {
    }

    public void interessentenlist(ActionEvent actionEvent) {
    }
}
