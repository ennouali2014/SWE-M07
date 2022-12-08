package de.unibremen.akademie.kursverwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    }

    public void abbrechen(ActionEvent actionEvent) {
    }

    public void teilnehmerlist(ActionEvent actionEvent) {
    }

    public void interessentenlist(ActionEvent actionEvent) {
    }
}
