package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PersonenListeController implements Initializable {


    public Tab fxmlPersonenListe;
    static public int index_of_selected_item;
    @FXML
    private TableColumn<Person, String> anrede;
    @FXML
    public TableColumn columnSelect;
    @FXML
    private TableColumn<Person, String> vorname;
    @FXML
    private TableColumn<Person, String> titel;
    @FXML
    private TableColumn<Person, String> strasse;
    @FXML
    private TableColumn<Person, String> plz;
    @FXML
    private TableColumn<Person, String> ort;
    @FXML
    private TableColumn<Person, String> email;
    @FXML
    private TableColumn<Person, String> telefon;
    @FXML
    private TableColumn<Person, Boolean> alleCheckBox;
    @FXML
    private Button andernButton;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private CheckBox interessentChkBox;
    @FXML
    private Label lblKursauswahl;
    @FXML
    private Button loeschButton;
    @FXML
    private Button personAnlegenButton;
    @FXML
    private TextField suchTxtField;
    @FXML
    private Button suchenButton;
    @FXML
    private CheckBox teilnehmerChkBox;
    @FXML
    private Button zurucksetzenButton;
    @FXML
    private TableColumn<Person, String> nachname;
    ObservableList<Person> list = FXCollections.observableArrayList();
    @FXML
    public TableView<Person> table;

    @FXML
    public void andernButtonAction(ActionEvent event) {
        if (!table.getSelectionModel().isEmpty()) {

            PersonenDetailsController.updateExistingPerson = true;
            KvModel.aktuellePerson = table.getSelectionModel().getSelectedItem();
            index_of_selected_item = table.getSelectionModel().getFocusedIndex();

            main.fxmlPersonenDetailsController.update(KvModel.aktuellePerson);

            PersonenDetailsController.zurueckwechseln = true;
            for (Tab tabPanePersonAnlegen : fxmlPersonenListe.getTabPane().getTabs()) {
                if (tabPanePersonAnlegen.getText().equals("Personen-Details")) {
                    tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);

                }
            }
        }
    }

    @FXML
    public void loeschButtonAction(ActionEvent event) {
        ObservableList<Person> allPerson = table.getItems();
        List<Person> selectedPersonCopy = new ArrayList<>(table.getSelectionModel().getSelectedItems());
        selectedPersonCopy.forEach(allPerson::remove);
    }

    @FXML
    public void personAnlegenButtonAction(ActionEvent event) {
        main.fxmlPersonenDetailsController.onabbrechenclick(event);
        PersonenDetailsController.zurueckwechseln = true;
        for (Tab tabPaneKursAnlegen : fxmlPersonenListe.getTabPane().getTabs()) {
            if (tabPaneKursAnlegen.getText().equals("Personen-Details")) {
                tabPaneKursAnlegen.getTabPane().getSelectionModel().select(tabPaneKursAnlegen);

            }
        }
    }

    @FXML
    void suchButtonAction(ActionEvent event) {

    }

    @FXML
    public void zurucksetzenButtonAction(ActionEvent event) {

    }

    private MainController main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        anrede.setCellValueFactory(new PropertyValueFactory<Person, String>("anrede"));
        anrede.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn("", "Herr", "Frau", "Divers"));
        anrede.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAnrede(t.getNewValue());
                    }
                }
        );

        titel.setCellValueFactory(new PropertyValueFactory<Person, String>("titel"));
        titel.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        titel.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setTitel(v.getNewValue());
                    }
                }
        );

        vorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        vorname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        vorname.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setVorname(v.getNewValue());
                    }
                }
        );

        nachname.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
        nachname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        nachname.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setNachname(v.getNewValue());
                    }
                }
        );


        strasse.setCellValueFactory(new PropertyValueFactory<Person, String>("strasse"));
        strasse.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        strasse.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setStrasse(v.getNewValue());
                    }
                }
        );

        plz.setCellValueFactory(new PropertyValueFactory<Person, String>("plz"));
        plz.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        plz.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setPlz(v.getNewValue());
                    }
                }
        );

        ort.setCellValueFactory(new PropertyValueFactory<Person, String>("ort"));
        ort.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        ort.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setOrt(v.getNewValue());
                    }
                }
        );

        email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        email.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        email.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setTelefon(v.getNewValue());
                    }
                }
        );

        telefon.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));
        telefon.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        telefon.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setTelefon(v.getNewValue());
                    }
                }
        );

        table.setItems(KvModel.personList);

        TableView.TableViewSelectionModel<Person> selectionModel =
                table.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.MULTIPLE);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                list = table.getSelectionModel().getSelectedItems();
            }
            if (list.size() > 1) {
                andernButton.setDisable(true);
            } else {
                andernButton.setDisable(false);
            }
        });

    }

    public void init(MainController mainController) {
        main=mainController;
    }
}


