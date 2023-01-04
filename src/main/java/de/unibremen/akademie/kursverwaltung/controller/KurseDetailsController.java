package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class KurseDetailsController {

    public Tab ContentKurseDetails;
    public Button clear;
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
    @FXML
    private ComboBox status;


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


        Kurs kurs = KvModel.model.addnewKurs(name, anzahl, zykls, startDate, minTn, maxTn, gebuhrB, mwstPro, kursBesch);

        LocalDate datetolocal = LocalDate.ofInstant(kurs.getEndeDatum().toInstant(), ZoneId.of("CET"));
        endeDatum.setValue(datetolocal);
        aktuelleTnZahl.setText(String.valueOf(kurs.getAktuelleTnZahl()));
        freiePlaetze.setText(String.valueOf(kurs.getFreiePlaetze()));
        mtwsEuro.setText(String.valueOf(kurs.getMwstEuro()));
        gebuehrNetto.setText(String.valueOf(kurs.getGebuehrNetto()));
        clear.fireEvent(actionEvent);
        for (Tab tabPaneKursListe : ContentKurseDetails.getTabPane().getTabs()) {
            if (tabPaneKursListe.getText().equals("Kurse-Liste")) {
                tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
            }

        }

        abbrechen(actionEvent);
    }

    public void abbrechen(ActionEvent actionEvent) {

        kursname.clear();
        status.setValue(status.getPromptText());
        anzahlTage.clear();
        zyklus.clear();

        startDatum.setValue(null);
        minTnZahl.clear();
        maxTnZahl.clear();
        gebuehrBrutto.clear();
        mtwsProzent.clear();
        kursBeschreibung.clear();

    }


    public void teilnehmerlist(ActionEvent actionEvent) {
    }

    public void interessentenlist(ActionEvent actionEvent) {
    }

    public void onDatePickerAction(ActionEvent actionEvent) {
    }
}
