package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel;
import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Meldung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// TODO: Datumsänderung wird nicht aktualaiesiert.

public class KurseDetailsController {

    @FXML
    public TextField txInpMwsProzent;
    @FXML
    private Tab tabKurseDetails;
    @FXML
    private TextField txInpKursname;
    @FXML
    private TextField txInpAnzahlTage;
    @FXML
    private TextField txInpZyklus;
    @FXML
    private DatePicker pickStartDatum;
    @FXML
    private DatePicker pickEndDatum;
    @FXML
    private TextField txInpMinTnZahl;
    @FXML
    private TextField txInpMaxTnZahl;
    @FXML
    private TextField txInpAktuelleTnZahl;
    @FXML
    private TextField txInpFreiePlaetze;
    @FXML
    private TextField txInpGebuehrBrutto;
    @FXML
    private TextField txInpGebuehrNetto;
    @FXML
    private TextField txInpMwsEuro;
    @FXML
    private TextArea txtAreaKursBeschreibung;
    @FXML
    private ComboBox comboStatus;
    private MainController main;

    public void onClickAbbrechenKurs(ActionEvent actionEvent) {
        txInpKursname.clear();
        comboStatus.setValue(comboStatus.getPromptText());
        txInpAnzahlTage.clear();
        txInpZyklus.clear();
        pickStartDatum.setValue(null);
        txInpMinTnZahl.clear();
        txInpMaxTnZahl.clear();
        txInpGebuehrBrutto.clear();
        txInpMwsProzent.clear();
        txtAreaKursBeschreibung.clear();
        pickEndDatum.setValue(null);
        txInpFreiePlaetze.clear();
        txInpAktuelleTnZahl.clear();
        txInpMwsEuro.clear();
        txInpGebuehrNetto.clear();
        if (AnwendungsModel.aktuellerKurs != null) {
            Tab plTab = main.fxmlKurseListeController.tabKurseListe;
            plTab.getTabPane().getSelectionModel().select(plTab);
        }
    }

    public void teilnehmerlist(ActionEvent actionEvent) {
        for (Tab tabPaneKursListe : tabKurseDetails.getTabPane().getTabs()) {
            if (tabPaneKursListe.getText().equals("Personen-Liste")) {
                tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
            }
        }
    }

    public void anzeigeZumAendernKurs(Kurs kurs) {
        if (kurs != null) {
            txInpKursname.setText(kurs.getName());
            comboStatus.setValue(kurs.getStatus());
            txInpAnzahlTage.setText(String.valueOf(kurs.getAnzahlTage()));
            txInpZyklus.setText(String.valueOf(kurs.getZyklus()));
            LocalDate datetolocal = LocalDate.ofInstant(kurs.getStartDatum().toInstant(), ZoneId.of("CET"));
            pickStartDatum.setValue(datetolocal);
            txInpMinTnZahl.setText(String.valueOf(kurs.getMinTnZahl()));
            txInpMaxTnZahl.setText(String.valueOf(kurs.getMaxTnZahl()));
            txInpGebuehrBrutto.setText(String.valueOf(kurs.getGebuehrBrutto()));
            txInpMwsProzent.setText(String.valueOf(kurs.getMwstProzent()));
            txtAreaKursBeschreibung.setText(kurs.getKursBeschreibung());
            LocalDate datelocal = LocalDate.ofInstant(kurs.getEndeDatum().toInstant(), ZoneId.of("CET"));
            pickEndDatum.setValue(datelocal);
            txInpFreiePlaetze.setText(String.valueOf(kurs.getFreiePlaetze()));
            txInpAktuelleTnZahl.setText(String.valueOf(kurs.getAktuelleTnZahl()));
            txInpMwsEuro.setText(String.valueOf(kurs.getMwstEuro()));
            txInpGebuehrNetto.setText(String.valueOf(kurs.getGebuehrNetto()));


        }
    }

    public void interessentenlist(ActionEvent actionEvent) {
    }

    public void onDatePickerAction(ActionEvent actionEvent) {
    }

    public void show() {
        tabKurseDetails.getTabPane().getSelectionModel().select(tabKurseDetails);
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    // FIXME: status leer gibt keinen Fehlermeldung
    public void onClickSaveKurs(ActionEvent actionEvent) {
        if (AnwendungsModel.aktuellerKurs != null) {
            // Bestehenden Kurs aendern
            try {
                AnwendungsModel.aktuellerKurs.setName(txInpKursname.getText());
                AnwendungsModel.aktuellerKurs.setAnzahlTage((Integer.parseInt(txInpAnzahlTage.getText())));
                AnwendungsModel.aktuellerKurs.setZyklus((Integer.parseInt(txInpZyklus.getText())));
                LocalDate localDate = pickStartDatum.getValue();
                AnwendungsModel.aktuellerKurs.setStartDatum(Date.from(localDate.atStartOfDay(ZoneId.of("CET")).toInstant()));
                AnwendungsModel.aktuellerKurs.setMinTnZahl((Integer.parseInt(txInpMinTnZahl.getText())));
                AnwendungsModel.aktuellerKurs.setMaxTnZahl((Integer.parseInt(txInpMaxTnZahl.getText())));
                AnwendungsModel.aktuellerKurs.setGebuehrBrutto((Double.parseDouble(txInpGebuehrBrutto.getText())));
                AnwendungsModel.aktuellerKurs.setMwstProzent((Double.parseDouble(txInpMwsProzent.getText())));
                AnwendungsModel.aktuellerKurs.setKursBeschreibung(txtAreaKursBeschreibung.getText());
                AnwendungsModel.aktuellerKurs.setEndeDatum();
                AnwendungsModel.aktuellerKurs.setGebuehrNetto();
                AnwendungsModel.aktuellerKurs.setFreiePlaetze();
                AnwendungsModel.aktuellerKurs.setMwstEuro();
                AnwendungsModel.aktuellerKurs.setAktuelleTnZahl();
                AnwendungsModel.aktuellerKurs.setStatus(comboStatus.getValue().toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                AnwendungsModel.aktuellerKurs.setDisplaystartDate(dateFormat.format(AnwendungsModel.aktuellerKurs.getStartDatum()));
                AnwendungsModel.aktuellerKurs.setDisplayEndeDate(dateFormat.format(AnwendungsModel.aktuellerKurs.getEndeDatum()));


            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            main.fxmlKurseListeController.tableKurseListe.refresh();
            main.fxmlPersonenDetailsController.tableKurse.refresh();

        } else {
            int anzahl = 0, zykls = 0, minTn = 0, maxTn = 0;
            double gebuhrB = 0, mwstPro = 0;
            LocalDate localDate;
            Date startDate = null;

            Kurs kurs;

            String name = txInpKursname.getText();
            String kursBesch = txtAreaKursBeschreibung.getText();
            String statusSTR = comboStatus.getSelectionModel().getSelectedItem().toString();
            try {
                if (!checkIsInt(txInpAnzahlTage.getText()) ||
                        !checkIsInt(txInpZyklus.getText()) ||
                        !checkIsInt(txInpMinTnZahl.getText()) ||
                        !checkIsInt(txInpMaxTnZahl.getText())) {
                    throw new IllegalArgumentException("Bitte nur ganze Zahlen (1) eingeben!");
                } else {
                    anzahl = Integer.parseInt(txInpAnzahlTage.getText());
                    zykls = Integer.parseInt(txInpZyklus.getText());
                    minTn = Integer.parseInt(txInpMinTnZahl.getText());
                    maxTn = Integer.parseInt(txInpMaxTnZahl.getText());
                }

                if (!checkIsDouble(txInpGebuehrBrutto.getText()) ||
                        !checkIsDouble(txInpMwsProzent.getText())) {
                    throw new IllegalArgumentException("Bitte nur Zahlen mit Nachkommastelle (1.0) eingeben!");
                } else {
                    gebuhrB = Double.parseDouble(txInpGebuehrBrutto.getText());
                    mwstPro = Double.parseDouble(txInpMwsProzent.getText());
                }

                if (!checkIsDate(String.valueOf(pickStartDatum.getValue()))) {
                    throw new IllegalArgumentException("Bitte Datum mit dem DatePicker wählen!");
                } else {
                    localDate = pickStartDatum.getValue();
                    startDate = Date.from(localDate.atStartOfDay(ZoneId.of("CET")).toInstant());
                }
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }

            try {
                kurs = Kurs.addNewKurs(name, anzahl, zykls, startDate, minTn, maxTn, gebuhrB, mwstPro, kursBesch, statusSTR);
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }

            LocalDate datetolocal = LocalDate.ofInstant(kurs.getEndeDatum().toInstant(), ZoneId.of("CET"));
            pickEndDatum.setValue(datetolocal);
            txInpAktuelleTnZahl.setText(String.valueOf(kurs.getAktuelleTnZahl()));
            txInpFreiePlaetze.setText(String.valueOf(kurs.getFreiePlaetze()));
            txInpMwsEuro.setText(String.valueOf(kurs.getMwstEuro()));
            txInpGebuehrNetto.setText(String.valueOf(kurs.getGebuehrNetto()));

        }
        for (Tab tabPaneKursListe : tabKurseDetails.getTabPane().getTabs()) {
            if (tabPaneKursListe.getText().equals("Kurse-Liste")) {
                tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
            }
        }
        onClickAbbrechenKurs(actionEvent);
    }

    // checks fuer die Umwandlungen beim Auslesen und Zuweisen der GUI-Felder
    public static boolean checkIsInt(String wert) {
        return wert.matches("\\d+");
    }

    public static boolean checkIsDouble(String wert) {
        return wert.matches("\\d+\\.\\d+");
    }

    public static boolean checkIsDate(String wert) {
        return wert.matches("^\s*((?:20)\\d{2})\\-(1[012]|0?[1-9])\\-(3[01]|[12][0-9]|0?[1-9])\s*$");
    }

}
