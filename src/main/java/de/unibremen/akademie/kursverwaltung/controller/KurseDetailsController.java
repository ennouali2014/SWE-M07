package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.application.CreatePdf;
import de.unibremen.akademie.kursverwaltung.application.DatumFormatieren;
import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Meldung;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import de.unibremen.akademie.kursverwaltung.domain.PersonKurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

// TODO: Datumsänderung wird nicht aktualaiesiert.

public class KurseDetailsController {

    // Zeile 293 ist auskommentiert, jetzt muss erstmal der Pfad nicht angepasst werden
    private final String pdfReader = "C:/Program Files/PDF24/pdf24-Reader.exe"; // Anpassen an den jeweiligen PC !!
    private final String pdfSpeicherPfad = "src/main/resources/de/unibremen/akademie/kursverwaltung/pdf/";

    @FXML
    public TextField txInpMwsProzent;
    @FXML
    public DatePicker pickAnwesenheitsDatum;
    @FXML
    public HBox hbxPrintAnwesenheitsliste;
    @FXML
    public HBox hbxCsvTeilnehmerliste;
    @FXML
    public Button btnKursSpeichern;
    @FXML
    public Button btnPersonAlsTeilnehmer;
    @FXML
    public Button btnTeilnehmerZuPerson;
    @FXML
    public Button btnPersonAlsInteressent;
    @FXML
    public Button btnInteressentenZuPerson;
    @FXML
    public Button btnInteressentZuTeilnehmer;
    @FXML
    public Button btnTeilnehmerZuInteressent;
    @FXML
    public TableView tablePerson;
    @FXML
    public TableView tableTeilnehmerPerson;
    @FXML
    public TableView tableInteressentenPerson;
    @FXML
    public TableColumn personName;
    @FXML
    public TableColumn personNachName;

    @FXML
    public TableColumn colTeilnahmeKursePersonName;
    @FXML
    public TableColumn colTeilnahmeKursePersonNachName;
    @FXML
    public TableColumn colInteresseKursePersonName;
    @FXML
    public TableColumn colInteresseKursePersonNachName;
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
    private MainController mainCtrl;
    private Object selectedItem;

    @FXML
    public void initialize() {
        // Anzeige im deutschen Format, nutzt Klasse DatumFormatieren im Application-Ordner
        DatumFormatieren.datumFormatieren(pickAnwesenheitsDatum);
        DatumFormatieren.datumFormatieren(pickStartDatum);
        DatumFormatieren.datumFormatieren(pickEndDatum);
        pickStartDatum.setPromptText("01.01.1970");
        pickEndDatum.setPromptText("Wird kalkuliert!");

        personName.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        personName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        personNachName.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
        personNachName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tablePerson.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        // TODO Kurs vom Teilnehmer in TeilnahmeKurse anzeigen!!
        colTeilnahmeKursePersonName.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        colTeilnahmeKursePersonName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colTeilnahmeKursePersonNachName.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
        colTeilnahmeKursePersonNachName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        // TODO Kurs vom Interessenten in InteresseKurse anzeigen!!
        colInteresseKursePersonName.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        colInteresseKursePersonName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colInteresseKursePersonNachName.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
        colInteresseKursePersonNachName.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        tablePerson.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkPersonTeilnehmerButton());
        tableTeilnehmerPerson.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkPersonAusTeilnehmerButton());
        tableInteressentenPerson.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkPersonInteressentenButton());

        tablePerson.setItems(kvModel.getPersonen().getPersonenListe());
        checkPersonTeilnehmerButton();
        checkPersonAusTeilnehmerButton();
        checkPersonInteressentenButton();

    }

    private void checkPersonTeilnehmerButton() {
        selectedItem = tablePerson.getSelectionModel().getSelectedItem();
        boolean disable = tableTeilnehmerPerson.getItems().contains(selectedItem) || tableInteressentenPerson.getItems().contains(selectedItem);
        btnPersonAlsTeilnehmer.setDisable(selectedItem == null || disable);
        btnPersonAlsInteressent.setDisable(selectedItem == null || disable);
    }

    private void checkPersonAusTeilnehmerButton() {
        selectedItem = tableTeilnehmerPerson.getSelectionModel().getSelectedItem();
        btnTeilnehmerZuPerson.setDisable(selectedItem == null);
        btnTeilnehmerZuInteressent.setDisable(selectedItem == null);
    }

    private void checkPersonInteressentenButton() {
        selectedItem = tableInteressentenPerson.getSelectionModel().getSelectedItem();
        btnInteressentZuTeilnehmer.setDisable(selectedItem == null);
        btnInteressentenZuPerson.setDisable(selectedItem == null);

    }

    // special thanx to chatGPT ;)
    private void pickAnwesenheitsDatumSetzen(LocalDate startDatum, LocalDate endDatum) {
        LocalDate aktuellesDatum = LocalDate.now();
        LocalDate value = startDatum;
        if (aktuellesDatum.isAfter(startDatum)) {
            value = aktuellesDatum;
        }
        pickAnwesenheitsDatum.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(startDatum) || item.isAfter(endDatum)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        pickAnwesenheitsDatum.setValue(value);
    }

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
        hbxPrintAnwesenheitsliste.setVisible(false);
        hbxCsvTeilnehmerliste.setVisible(false);
        btnKursSpeichern.setText("Speichern");
        if (kvModel.aktuellerKurs != null) {
            Tab plTab = mainCtrl.fxmlKurseListeController.tabKurseListe;
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
            btnKursSpeichern.setText("Update");
            if (hatKursTeilnehmer()) {
                // Auswahldatum auf die Dauer des Kurses einschränken
                pickAnwesenheitsDatumSetzen(pickStartDatum.getValue(), pickEndDatum.getValue());
                hbxPrintAnwesenheitsliste.setVisible(true);
                hbxCsvTeilnehmerliste.setVisible(true);
            } else {
                hbxPrintAnwesenheitsliste.setVisible(false);
                hbxCsvTeilnehmerliste.setVisible(false);
            }

            tableTeilnehmerPerson.getItems().clear();
            tableTeilnehmerPerson.getItems().addAll(kvModel.getPkListe().getPersonen(kurs, true));
            tableInteressentenPerson.getItems().clear();
            tableInteressentenPerson.getItems().addAll(kvModel.getPkListe().getPersonen(kurs, false));
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
        mainCtrl = mainController;
    }

    // FIXME: status leer gibt keinen Fehlermeldung
    public void onClickSaveKurs(ActionEvent actionEvent) {
        if (kvModel.aktuellerKurs != null) {
            // Bestehenden Kurs aendern
            try {
                kvModel.aktuellerKurs.setName(txInpKursname.getText());
                kvModel.aktuellerKurs.setAnzahlTage((Integer.parseInt(txInpAnzahlTage.getText())));
                kvModel.aktuellerKurs.setZyklus((Integer.parseInt(txInpZyklus.getText())));
                LocalDate localDate = pickStartDatum.getValue();
                kvModel.aktuellerKurs.setStartDatum(Date.from(localDate.atStartOfDay(ZoneId.of("CET")).toInstant()));
                kvModel.aktuellerKurs.setMinTnZahl((Integer.parseInt(txInpMinTnZahl.getText())));
                kvModel.aktuellerKurs.setMaxTnZahl((Integer.parseInt(txInpMaxTnZahl.getText())));
                kvModel.aktuellerKurs.setGebuehrBrutto((Double.parseDouble(txInpGebuehrBrutto.getText())));
                kvModel.aktuellerKurs.setMwstProzent((Double.parseDouble(txInpMwsProzent.getText())));
                kvModel.aktuellerKurs.setKursBeschreibung(txtAreaKursBeschreibung.getText());
                kvModel.aktuellerKurs.setEndeDatum();
                kvModel.aktuellerKurs.setGebuehrNetto();
                kvModel.aktuellerKurs.setFreiePlaetze();
                kvModel.aktuellerKurs.setMwstEuro();
                kvModel.aktuellerKurs.setAktuelleTnZahl(kvModel.aktuellerKurs.getAktuelleTnZahl());
                kvModel.aktuellerKurs.setStatus(comboStatus.getValue().toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                kvModel.aktuellerKurs.setDisplaystartDate(dateFormat.format(kvModel.aktuellerKurs.getStartDatum()));
                kvModel.aktuellerKurs.setDisplayEndeDate(dateFormat.format(kvModel.aktuellerKurs.getEndeDatum()));

                if (hatKursTeilnehmer()) {
                    hbxCsvTeilnehmerliste.setVisible(true);
                    hbxPrintAnwesenheitsliste.setVisible(true);
                }

                // TODO Mohammed 04.02

//                kvModel.getPkListe().removeAllKurseAlsTeilnehmer(kvModel.aktuellePerson);
//                kvModel.getPkListe().removeAllKurseAlsInteressent(kvModel.aktuellePerson);
//
//                kvModel.getPkListe().addTeilnehmerInKurs(kvModel.aktuellePerson, this.tableViewTeilnehmerZu.getItems());
//                kvModel.getPkListe().addInteressentInKurs(kvModel.aktuellePerson, this.tableViewInteressentenZu.getItems());

                //  TODO 04.02

            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            btnKursSpeichern.setText("Speichern");
            mainCtrl.fxmlKurseListeController.tableKurseListe.refresh();
            mainCtrl.fxmlPersonenDetailsController.tableKurse.refresh();

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
                if (comboStatus.getSelectionModel().getSelectedIndex() == -1) {
                    throw new IllegalArgumentException("Bitte einen Kurs-Status eingeben");
                } else {
                    statusSTR = comboStatus.getSelectionModel().getSelectedItem().toString();
                }
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }

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
                kurs = kvModel.getKurse().addNewKurs(name, anzahl, zykls, startDate, minTn, maxTn, gebuhrB, mwstPro, kursBesch, statusSTR);
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
//    @FXML
//    private TableView tableViewTeilnehmerZu;

//    private TableView tableViewInteressentenZu;
//        for (Tab tabPaneKursListe : tabKurseDetails.getTabPane().getTabs()) {
//            if (tabPaneKursListe.getText().equals("Kurse-Liste")) {
//                tabPaneKursListe.getTabPane().getSelectionModel().select(tabPaneKursListe);
//            }
//        }
//        onClickAbbrechenKurs(actionEvent);
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

    public void onClickPrintAnwesenheitsliste(ActionEvent actionEvent) {
        if (kvModel.aktuellerKurs != null) {
            try {
                LocalDate localDate = pickAnwesenheitsDatum.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String datumAnwesenheitsliste = localDate.format(formatter);
                new CreatePdf().createAnwesenheitslistePdf(kvModel.aktuellerKurs.getName(), datumAnwesenheitsliste);
                String erstelltesPdf = "Anwesenheitsliste_" + kvModel.aktuellerKurs.getName().replace(" ", "_") + "_" + datumAnwesenheitsliste + ".pdf";
                /*ProcessBuilder pb = new ProcessBuilder(pdfReader, pdfSpeicherPfad + erstelltesPdf);
                Thread.sleep(500); // 1,5 Sekunden warten
                pb.start();*/
                // Nach Druck zurück zur Liste
                Tab plTab = mainCtrl.fxmlKurseListeController.tabKurseListe;
                plTab.getTabPane().getSelectionModel().select(plTab);
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
            }
        }
    }

    public boolean hatKursTeilnehmer() {
        int teilnehmendePersonen = 0;
        for (PersonKurs personKurs : kvModel.getPkListe().personKursList) {
            if (personKurs.getKurs().getName().equals(kvModel.aktuellerKurs.getName()) && personKurs.isTeilnehmer()) {
                Person person = personKurs.getPerson();
                teilnehmendePersonen++;
            }
        }
        // todo: Abgleich mit MindestTeilnehmerAnzahl ??
        return teilnehmendePersonen > 0;
    }

    public void onClickCsvTeilnehmerliste(ActionEvent actionEvent) {
        if (hatKursTeilnehmer()) {
            hbxCsvTeilnehmerliste.setVisible(true);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Speichern unter");
            fileChooser.setInitialFileName(kvModel.aktuellerKurs.getName().replace(" ","_"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV-Datei (*.csv)", "*.csv"));
            File file = fileChooser.showSaveDialog(mainCtrl.mainStage);
            List<Person> pkListe = kvModel.getPkListe().getPersonAlsTeilnehmer(kvModel.aktuellerKurs);
            if (file != null) {
                try {
                    FileWriter writer = new FileWriter(file);
                    String csvTrenner = pkListe.get(0).getCSVTRENNER();
                    writer.append("Anrede" + csvTrenner +
                            "Titel" + csvTrenner +
                            "Vorname" + csvTrenner +
                            "Nachname" + csvTrenner +
                            "Strasse" + csvTrenner +
                            "PLZ" + csvTrenner +
                            "Ort" + csvTrenner +
                            "E-Mail" + csvTrenner +
                            "telefon" + csvTrenner +
                            '\n');
                    for (Person p : pkListe) {
                        writer.append(p.toCsv());
                        writer.append('\n');
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("CSV-Datei wurde erfolgreich gespeichert.");
                } catch (Exception e) {
                    Meldung.eingabeFehler(("Fehler beim Speichern der CSV-Datei: " + e.getMessage()));
                }
            }
        }
    }

    // for test only
    MainController getMainCtrl() {
        return mainCtrl;
    }

    public void onClickPersonZuTeilnehmer(ActionEvent actionEvent) {
        tableTeilnehmerPerson.getItems().add(tablePerson.getSelectionModel().getSelectedItem());
        tablePerson.getSelectionModel().clearSelection();
    }

    public void onClickPersonZuInteressent(ActionEvent actionEvent) {

        tableInteressentenPerson.getItems().add(tablePerson.getSelectionModel().getSelectedItem());
        tablePerson.getSelectionModel().clearSelection();

    }

    public void onClickTeilnehmerZuInteressent(ActionEvent actionEvent) {
        //System.out.println("Teilnehmer zu Interessent!");
        tableInteressentenPerson.getItems().add(tableTeilnehmerPerson.getSelectionModel().getSelectedItem());
        tableTeilnehmerPerson.getItems().removeAll(tableTeilnehmerPerson.getSelectionModel().getSelectedItems());

    }

    public void onClickInteressentZuTeilnehmer(ActionEvent actionEvent) {
        tableTeilnehmerPerson.getItems().add(tableInteressentenPerson.getSelectionModel().getSelectedItem());
        tableInteressentenPerson.getItems().removeAll(tableInteressentenPerson.getSelectionModel().getSelectedItems());

    }

    public void onClickPersonRausAusInteressent(ActionEvent actionEvent) {
        tableInteressentenPerson.getItems().removeAll(tableInteressentenPerson.getSelectionModel().getSelectedItem());

    }

    public void onClickPersonRausAusTeilnehmer(ActionEvent actionEvent) {
        tableTeilnehmerPerson.getItems().remove(tableTeilnehmerPerson.getSelectionModel().getSelectedItem());
    }


}
