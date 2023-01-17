package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Meldung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class KurseDetailsController {

    @FXML
    private Tab fxmlKurseDetails;

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
    public TextField mtwsProzent;
    @FXML
    private TextArea kursBeschreibung;
    @FXML
    private ComboBox status;

    private MainController main;

    public void apply(ActionEvent actionEvent) {
        if( KvModel.aktuelleKurs !=null) {
            // Bestehenden Kurs aendern
            try {
                KvModel.aktuelleKurs.setName(kursname.getText());
                KvModel.aktuelleKurs.setAnzahlTage((Integer.parseInt(anzahlTage.getText())));
                KvModel.aktuelleKurs.setZyklus((Integer.parseInt(zyklus.getText())));
                LocalDate localDate = startDatum.getValue();
                KvModel.aktuelleKurs.setStartDatum(Date.from(localDate.atStartOfDay(ZoneId.of("CET")).toInstant()));
                KvModel.aktuelleKurs.setMinTnZahl((Integer.parseInt(minTnZahl.getText())));
                KvModel.aktuelleKurs.setMaxTnZahl((Integer.parseInt(maxTnZahl.getText())));
                KvModel.aktuelleKurs.setGebuehrBrutto((Double.parseDouble(gebuehrBrutto.getText())));
                KvModel.aktuelleKurs.setMwstProzent((Double.parseDouble(mtwsProzent.getText())));
                KvModel.aktuelleKurs.setKursBeschreibung(kursBeschreibung.getText());
                KvModel.aktuelleKurs.setEndeDatum();
                KvModel.aktuelleKurs.setGebuehrNetto();
                KvModel.aktuelleKurs.setFreiePlaetze();
                KvModel.aktuelleKurs.setMwstEuro();
                KvModel.aktuelleKurs.setAktuelleTnZahl();
                KvModel.aktuelleKurs.setStatus(status.getValue().toString());
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            main.fxmlKurseListeController.tableView.refresh();
            main.fxmlPersonenDetailsController.tableViewKurse.refresh();

        }else {
            // Neuen Kurs anlegen
            try {
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
                Kurs kurs = Kurs.addNewKurs(name, anzahl, zykls, startDate, minTn, maxTn, gebuhrB, mwstPro, kursBesch);
                LocalDate datetolocal = LocalDate.ofInstant(kurs.getEndeDatum().toInstant(), ZoneId.of("CET"));
                endeDatum.setValue(datetolocal);
                aktuelleTnZahl.setText(String.valueOf(kurs.getAktuelleTnZahl()));
                freiePlaetze.setText(String.valueOf(kurs.getFreiePlaetze()));
                mtwsEuro.setText(String.valueOf(kurs.getMwstEuro()));
                gebuehrNetto.setText(String.valueOf(kurs.getGebuehrNetto()));

            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }

        }
        for (Tab tabPaneKursListe : fxmlKurseDetails.getTabPane().getTabs()) {
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
        endeDatum.setValue(null);
        freiePlaetze.clear();
        aktuelleTnZahl.clear();
        mtwsEuro.clear();
        gebuehrNetto.clear();
        if( KvModel.aktuelleKurs !=null){
            for (Tab tabPaneKursListe : fxmlKurseDetails.getTabPane().getTabs()) {
                if (tabPaneKursListe.getText().equals("Kurse-Liste")) {
                    tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
                }
            }
        }

    }



    public void teilnehmerlist(ActionEvent actionEvent) {

        for (Tab tabPaneKursListe : fxmlKurseDetails.getTabPane().getTabs()) {
            if (tabPaneKursListe.getText().equals("Personen-Liste")) {
                tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
            }

        }
    }
    public void update(Kurs kurs) {
        if(kurs!=null){
            kursname.setText(kurs.getName());
            status.setValue(kurs.getStatus());
            anzahlTage.setText(String.valueOf(kurs.getAnzahlTage()));
            zyklus.setText(String.valueOf(kurs.getZyklus()));
            LocalDate datetolocal = LocalDate.ofInstant(kurs.getStartDatum().toInstant(), ZoneId.of("CET"));
            startDatum.setValue(datetolocal);
            minTnZahl.setText(String.valueOf(kurs.getMinTnZahl()));
            maxTnZahl.setText(String.valueOf(kurs.getMaxTnZahl()));
            gebuehrBrutto.setText(String.valueOf(kurs.getGebuehrBrutto()));
            mtwsProzent.setText(String.valueOf(kurs.getMwstProzent()));
            kursBeschreibung.setText(kurs.getKursBeschreibung());
            LocalDate datelocal = LocalDate.ofInstant(kurs.getEndeDatum().toInstant(), ZoneId.of("CET"));
            endeDatum.setValue(datelocal);
            freiePlaetze.setText(String.valueOf(kurs.getFreiePlaetze()));
            aktuelleTnZahl.setText(String.valueOf(kurs.getAktuelleTnZahl()));
            mtwsEuro.setText(String.valueOf(kurs.getMwstEuro()));
            gebuehrNetto.setText(String.valueOf(kurs.getGebuehrNetto()));
        }
    }

    public void interessentenlist(ActionEvent actionEvent) {
    }

    public void onDatePickerAction(ActionEvent actionEvent) {
    }
    public void init(MainController mainController) {
        main=mainController;
    }

    public void show() {
        fxmlKurseDetails.getTabPane().getSelectionModel().select(fxmlKurseDetails);
    }

}
