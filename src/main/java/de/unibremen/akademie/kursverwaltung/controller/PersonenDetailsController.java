package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel;
import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Meldung;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;
import static de.unibremen.akademie.kursverwaltung.domain.KvModel.pkListe;

public class PersonenDetailsController {
    @FXML
    public ChoiceBox choiceAnrede;
    @FXML
    public TextField txInpTitel;
    @FXML
    public TextField txInpVorname;
    @FXML
    public TextField txInpNachname;
    @FXML
    public TextField txInpStrasse;
    @FXML
    public TextField txInpPlz;
    @FXML
    public TextField txInpEmail;
    @FXML
    public TextField txInpTelefon;
    @FXML
    public TextField txInpOrt;
    @FXML
    public Button btnSavePersonDetails;
    @FXML
    public Button btnCancelPersonDetails;
    public ObservableList<String> choiceListAnrede = FXCollections.observableArrayList();
    static public final ObservableList<Person> listKursTeilnehmer = FXCollections.observableArrayList();
    static public final ObservableList<Person> listKursInteressent = FXCollections.observableArrayList();
    @FXML
    public Tab tabPersonenDetails;
    static public boolean zurueckPersonenliste = false;
    static public boolean bearbeiten = false;
    @FXML
    public TableView tableKurse;
    @FXML
    public TableColumn colKurseKursname;
    @FXML
    public TableColumn colKurseStartDate;
    @FXML
    public TableView tableTeilnahmeKurse;
    @FXML
    public TableColumn colTeilnahmeKurseKursname;
    @FXML
    public TableView tableInteresseKurse;
    @FXML
    public TableColumn colInteresseKurseKursname;
    @FXML
    public Button btnInteressentZuTeilnehmer;
    @FXML
    public Button btnTeilnehmerZuInteressent;
    @FXML
    public Button btnInteressentKursRaus;
    @FXML
    public Button btnInteressentKursRein;
    @FXML
    public Button btnTeilnehmerKursRaus;
    @FXML
    public Button btnTeilnehmerKursRein;

    private MainController main;
    private Object selectedItem;

    public void init(MainController mainController) {
        main = mainController;
    }

    public void initialize() {
        choiceListAnrede.add("");
        choiceListAnrede.add("Herr");
        choiceListAnrede.add("Frau");
        choiceListAnrede.add("Divers");
        choiceAnrede.setItems(choiceListAnrede);
        choiceAnrede.getSelectionModel().selectFirst();
        colKurseKursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        colKurseKursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());
        colKurseStartDate.setCellValueFactory(new PropertyValueFactory<Kurs, String>("displaystartDate"));
        colKurseStartDate.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());


        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableKurse.getSelectionModel();

        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        colTeilnahmeKurseKursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        colTeilnahmeKurseKursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        colInteresseKurseKursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        colInteresseKurseKursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        tableKurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkKursTeilnehmerButton());
        tableTeilnahmeKurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkKursAusTeilnehmerButton());
        tableInteresseKurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkKursInteressentenButton());

        tableKurse.setItems(kvModel.getKurse().getKursListe());
        checkKursTeilnehmerButton();
        checkKursAusTeilnehmerButton();
        checkKursInteressentenButton();
    }

    private void checkKursTeilnehmerButton() {
        selectedItem = tableKurse.getSelectionModel().getSelectedItem();
        boolean disable = tableTeilnahmeKurse.getItems().contains(selectedItem) || tableInteresseKurse.getItems().contains(selectedItem);
        btnTeilnehmerKursRein.setDisable(selectedItem == null || disable);
        btnInteressentKursRein.setDisable(selectedItem == null || disable);
    }

    private void checkKursAusTeilnehmerButton() {
        selectedItem = tableTeilnahmeKurse.getSelectionModel().getSelectedItem();
        btnTeilnehmerKursRaus.setDisable(selectedItem == null);
        btnTeilnehmerZuInteressent.setDisable(selectedItem == null);
    }

    private void checkKursInteressentenButton() {
        selectedItem = tableInteresseKurse.getSelectionModel().getSelectedItem();
        btnInteressentZuTeilnehmer.setDisable(selectedItem == null);
        btnInteressentKursRaus.setDisable(selectedItem == null);

    }
    @FXML
    public void onClickSavePerson() {
        Person person = null;
        // Update einer bestehenden Person
        if (AnwendungsModel.aktuellePerson != null) {
            try {
                AnwendungsModel.aktuellePerson.updatePerson(choiceAnrede.getValue().toString(), txInpTitel.getText(), txInpVorname.getText(),
                        txInpNachname.getText(), txInpStrasse.getText(), txInpPlz.getText(), txInpOrt.getText(), txInpEmail.getText(), txInpTelefon.getText());
                pkListe.addKurseAlsTeilnehmer(AnwendungsModel.aktuellePerson, this.tableTeilnahmeKurse.getItems());
                pkListe.addKurseAlsInteressent(AnwendungsModel.aktuellePerson, this.tableInteresseKurse.getItems());
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            felderLeeren();
            btnSavePersonDetails.setText("Speichern");
        } else {
            // Neue Person hinzufuegen
            int aktuelleAnzPersonen = kvModel.getPersonen().getPersonenListe().size();
            try {
                person = Person.addNewPerson(choiceAnrede.getValue().toString(), txInpTitel.getText(), txInpVorname.getText(),
                        txInpNachname.getText(), txInpStrasse.getText(), txInpPlz.getText(), txInpOrt.getText(), txInpEmail.getText(), txInpTelefon.getText());
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            if (kvModel.getPersonen().getPersonenListe().size() > aktuelleAnzPersonen) {
                felderLeeren();
            }
        }
        AnwendungsModel.aktuellePerson = null;
        Tab plTab = main.fxmlPersonenListeController.tabPersonenListe;
        //plTab.getTabPane().getSelectionModel().select(plTab);
        main.fxmlPersonenListeController.tablePersonenListe.refresh();

        if (PersonenDetailsController.zurueckPersonenliste) {
            plTab.getTabPane().getSelectionModel().select(plTab);

            main.fxmlPersonenListeController.tablePersonenListe.getSelectionModel().clearSelection();
            main.fxmlPersonenListeController.tablePersonenListe.getSelectionModel().select(person);
        }
    }
    @FXML
    public void onClickAnzeigeAendernPerson(Person person) {
        if (person != null) {
            String anrede = person.getAnrede();
            for (int i = 0; i < choiceListAnrede.size(); i++) {
                if (choiceListAnrede.get(i).equals(anrede)) {
                    this.choiceAnrede.getSelectionModel().select(i);
                }
            }
            this.txInpTitel.setText(person.getTitel());
            this.txInpVorname.setText(person.getVorname());
            this.txInpNachname.setText(person.getNachname());
            this.txInpStrasse.setText(person.getStrasse());
            this.txInpPlz.setText(person.getPlz());
            this.txInpOrt.setText(person.getOrt());
            this.txInpEmail.setText(person.getEmail());
            this.txInpTelefon.setText(person.getTelefon());
        }
    }
    @FXML
    public void onClickAbbrechenPerson(ActionEvent event) {
        felderLeeren();

        if (zurueckPersonenliste) {
            //System.out.println(zurueckPersonenliste);
            Tab plTab = main.fxmlPersonenListeController.tabPersonenListe;
            plTab.getTabPane().getSelectionModel().select(plTab);
            zurueckPersonenliste = false;
        }
    }

    public void felderLeeren() {
        choiceAnrede.getSelectionModel().selectFirst();
        txInpTitel.clear();
        txInpVorname.clear();
        txInpNachname.clear();
        txInpStrasse.clear();
        txInpPlz.clear();
        txInpOrt.clear();
        txInpEmail.clear();
        txInpTelefon.clear();
        tableTeilnahmeKurse.getItems().clear();
        tableInteresseKurse.getItems().clear();
        btnSavePersonDetails.setText("Speichern");
    }

    public void onClickTeilnehmerZuInteressent(ActionEvent actionEvent) {
        if (AnwendungsModel.aktuellePerson == null || tableTeilnahmeKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = pkListe.addPersonInKursAlsTeilnehmer(AnwendungsModel.aktuellePerson,
                (Kurs) tableKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            System.out.println("Teilnehmer zu Interessent!");
            tableInteresseKurse.getItems().add(tableTeilnahmeKurse.getSelectionModel().getSelectedItem());
            tableTeilnahmeKurse.getItems().removeAll(tableTeilnahmeKurse.getSelectionModel().getSelectedItems());
            // checkKursTeilnehmerButton();
        }
    }

    //TODO Implementierung ist noch nicht fertig!
    public void onClickInteressentZuTeilnehmer(ActionEvent actionEvent) {
        if (AnwendungsModel.aktuellePerson == null || tableInteresseKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = pkListe.addPersonInKursAlsTeilnehmer(AnwendungsModel.aktuellePerson,
                (Kurs) tableKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            System.out.println("Interessent zu Teilnehmer!");
            tableTeilnahmeKurse.getItems().add(tableInteresseKurse.getSelectionModel().getSelectedItem());
            tableInteresseKurse.getItems().removeAll(tableInteresseKurse.getSelectionModel().getSelectedItems());
            // checkKursTeilnehmerButton();
        }
    }

    public void onClickKursRausAusInteressent(ActionEvent actionEvent) {
        tableInteresseKurse.getItems().removeAll(tableInteresseKurse.getSelectionModel().getSelectedItem());
    }

    public void onClickKursRausAusTeilnehmer(ActionEvent actionEvent) {
        tableTeilnahmeKurse.getItems().remove(tableTeilnahmeKurse.getSelectionModel().getSelectedItem());
    }

    /*
        public void kursZuTeilnehmer(ActionEvent actionEvent) {

            if (KvModel.aktuellePerson == null || tableViewKurse.getSelectionModel().getSelectedItem() == null) {
                return;
            }

            Kurs kurs = (Kurs) tableViewKurse.getSelectionModel().getSelectedItem();
            KvModel.aktuellePerson.addKursTeilnehmer(kurs); //
            tableViewTeilnehmerZu.getItems().add(kurs);

            System.out.println(KvModel.aktuellePerson);
            System.out.println(tableViewKurse.getSelectionModel().getSelectedItem());


        }
    */

    public void onClickKursZuTeilnehmer(ActionEvent actionEvent) {
        if (AnwendungsModel.aktuellePerson == null || tableKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = pkListe.addPersonInKursAlsTeilnehmer(AnwendungsModel.aktuellePerson,
                (Kurs) tableKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            tableTeilnahmeKurse.getItems().add(tableKurse.getSelectionModel().getSelectedItem());
            tableKurse.getSelectionModel().clearSelection();
            //        checkKursTeilnehmerButton();
            // FIXME: Falls schon in InteressentView ist, dort dann rausnehmen (AxF)
        }
    }

    public void onClickKursZuInteressent(ActionEvent actionEvent) {

        if (AnwendungsModel.aktuellePerson == null || tableKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = pkListe.addPersonInKursAlsInteressent(AnwendungsModel.aktuellePerson,
                (Kurs) tableKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            tableInteresseKurse.getItems().add(tableKurse.getSelectionModel().getSelectedItem());
            tableKurse.getSelectionModel().clearSelection();
            //        checkKursInteressentenButton();
            // FIXME: Falls schon in TeilnehmerView ist, dort dann rausnehmen (AxF)
        }
    }
}
