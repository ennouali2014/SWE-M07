package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Meldung;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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


        tableKurse.setItems(KvModel.kvModel.kursList);
        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableKurse.getSelectionModel();


        tableKurse.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        colTeilnahmeKurseKursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        colTeilnahmeKurseKursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        colInteresseKurseKursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        colInteresseKurseKursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        tableKurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkKursTeilnehmerButton());
        tableKurse.itemsProperty().addListener((observable, oldValue, newValue) -> checkKursTeilnehmerButton());

        tableTeilnahmeKurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkKursAusTeilnehmerButton());
        tableTeilnahmeKurse.itemsProperty().addListener((observable, oldValue, newValue) -> {
            checkKursAusTeilnehmerButton();
            checkKursTeilnehmerButton();
        });

        tableInteresseKurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkKursInteressentenButton());
        tableInteresseKurse.itemsProperty().addListener((observable, oldValue, newValue) -> checkKursInteressentenButton());
    }

    private void checkKursTeilnehmerButton() {
        selectedItem = tableKurse.getSelectionModel().getSelectedItem();
        boolean disable = tableTeilnahmeKurse.getItems().contains(selectedItem) || tableInteresseKurse.getItems().contains(selectedItem);
        if (selectedItem != null) {
            btnTeilnehmerKursRein.setDisable(disable);
            btnInteressentKursRein.setDisable(disable);
        } else {
            btnTeilnehmerKursRein.setDisable(true);
        }
    }

    private void checkKursAusTeilnehmerButton() {
        selectedItem = tableTeilnahmeKurse.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnTeilnehmerKursRaus.setDisable(false);
        } else {
            btnTeilnehmerKursRaus.setDisable(true);
        }
    }

    private void checkKursInteressentenButton() {
        selectedItem = tableInteresseKurse.getSelectionModel().getSelectedItem();
        boolean disable = tableTeilnahmeKurse.getItems().contains(selectedItem) || tableKurse.getItems().contains(selectedItem);
        if (selectedItem != null) {
            btnInteressentKursRein.setDisable(disable);
        } else {
            btnTeilnehmerKursRein.setDisable(true);
            btnInteressentKursRein.setDisable(true);
        }
    }
   /* private void checkKursVonInteressentZuTeilnehmerButton() {
        selectedItem = tableViewInteresseKurse.getSelectionModel().getSelectedItem();
        boolean disable = tableViewTeilnahmeKurse.getItems().contains(selectedItem) || tableViewInteresseKurse.getItems().contains(selectedItem);
        if (selectedItem != null) {
            btnInteressentZuTeilnehmer.setDisable(disable);
            btnInteressentKursRein.setDisable(disable);
        } else {
            btnTeilnehmerKursRein.setDisable(true);
        }*/


    @FXML
    public void onClickSavePerson() {
        Person person = null;
        // Update einer bestehenden Person
        if (KvModel.aktuellePerson != null) {
            try {
                KvModel.aktuellePerson.updatePerson(choiceAnrede.getValue().toString(), txInpTitel.getText(), txInpVorname.getText(), txInpNachname.getText(), txInpStrasse.getText(), txInpPlz.getText(), txInpOrt.getText(), txInpEmail.getText(), txInpTelefon.getText());
                pkListe.addKurseAlsTeilnehmer(KvModel.aktuellePerson, this.tableTeilnahmeKurse.getItems());
                pkListe.addKurseAlsInteressent(KvModel.aktuellePerson, this.tableInteresseKurse.getItems());
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            felderLeeren();
            btnSavePersonDetails.setText("Speichern");
        } else {
            // Neue Person hinzufuegen
            int aktuelleAnzPersonen = KvModel.personList.size();
            try {
                person = Person.addNewPerson(choiceAnrede.getValue().toString(), txInpTitel.getText(), txInpVorname.getText(), txInpNachname.getText(), txInpStrasse.getText(), txInpPlz.getText(), txInpOrt.getText(), txInpEmail.getText(), txInpTelefon.getText());
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            if (KvModel.personList.size() > aktuelleAnzPersonen) {
                felderLeeren();
            }
        }
        KvModel.aktuellePerson = null;
        Tab plTab = main.fxmlPersonenListeController.fxmlPersonenListe;
        //plTab.getTabPane().getSelectionModel().select(plTab);
        main.fxmlPersonenListeController.table.refresh();

        if (PersonenDetailsController.zurueckPersonenliste) {
            plTab.getTabPane().getSelectionModel().select(plTab);

            main.fxmlPersonenListeController.table.getSelectionModel().clearSelection();
            main.fxmlPersonenListeController.table.getSelectionModel().select(person);
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
            Tab plTab = main.fxmlPersonenListeController.fxmlPersonenListe;
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
    }

    public void onClickInteressentZuTeilnehmer(ActionEvent actionEvent) {
//        selectedItem = tableViewInteresseKurse.getSelectionModel().getSelectedItem();
//
//        tableViewTeilnahmeKurse.setItems(tableViewInteresseKurse.getSelectionModel().getSelectedItems());
//        tableViewInteresseKurse.getItems().removeAll(selectedItem);
    }

    public void onClickKursRausAusInteressent(ActionEvent actionEvent) {
        tableInteresseKurse.getItems().removeAll(tableInteresseKurse.getSelectionModel().getSelectedItem());
    }

    public void onClickKursRausAusTeilnehmer(ActionEvent actionEvent) {
        tableTeilnahmeKurse.getItems().removeAll(tableTeilnahmeKurse.getSelectionModel().getSelectedItem());
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
        if (KvModel.aktuellePerson == null || tableKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = pkListe.addPersonInKursAlsTeilnehmer(KvModel.aktuellePerson,
                (Kurs) tableKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            tableTeilnahmeKurse.getItems().add(tableKurse.getSelectionModel().getSelectedItem());
            checkKursTeilnehmerButton();
            // FIXME: Falls schon in InteressentView ist, dort dann rausnehmen (AxF)
        }

    }


    public void onClickKursZuInteressent(ActionEvent actionEvent) {

        if (KvModel.aktuellePerson == null || tableKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = pkListe.addPersonInKursAlsInteressent(KvModel.aktuellePerson,
                (Kurs) tableKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            tableInteresseKurse.getItems().add(tableKurse.getSelectionModel().getSelectedItem());
            checkKursInteressentenButton();
            // FIXME: Falls schon in TeilnehmerView ist, dort dann rausnehmen (AxF)
        }

    }
}
