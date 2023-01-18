package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import de.unibremen.akademie.kursverwaltung.domain.PersonKurs;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    @FXML
    public TableColumn<Person, String> kursTeilnahmeStr;

    @FXML
    private TableColumn<Person, String> anrede;
    @FXML
    public TableColumn columnSelect;
    // TODO wid noch bearbeitet! Mohammed
    String listPersonDetails[] = {"titel", "vorname", "nachname", "strasse", "plz", "ort", "email", "telefon"};
    @FXML
    private TableColumn<Person, String> nachname;
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
    private TableColumn<Person, String> vorname;
    @FXML
    private SplitPane splitPane;
    private ObservableList<Person> list = FXCollections.observableArrayList();
    @FXML
    private Button suchenButton;
    @FXML
    private CheckBox teilnehmerChkBox;
    @FXML
    private Button zurucksetzenButton;
    @FXML
    public TableView<Person> table;
    @FXML

    private FilteredList<Person> filteredData;

    @FXML
    public void loeschButtonAction(ActionEvent event) {
        ObservableList<Person> allPerson = KvModel.model.personList;
        List<Person> selectedPersonCopy = new ArrayList<>(table.getSelectionModel().getSelectedItems());
        selectedPersonCopy.forEach(allPerson::remove);
    }

    @FXML
    public void personAnlegenButtonAction(ActionEvent event) {

        KvModel.aktuellePerson = null;
        main.fxmlPersonenDetailsController.felderLeeren();
        PersonenDetailsController.zurueckPersonenliste = true;
        for (Tab tabPanePersonAnlegen : fxmlPersonenListe.getTabPane().getTabs()) {
            if (tabPanePersonAnlegen.getText().equals("Personen-Details")) {
                tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);

            }
        }
    }

    String searchpattern;

    @FXML
    public void andernButtonAction(ActionEvent event) {
        PersonenDetailsController.zurueckPersonenliste = true;

        if (!table.getSelectionModel().isEmpty()) {
            main.fxmlPersonenDetailsController.save.setText("Update");

            KvModel.aktuellePerson = table.getSelectionModel().getSelectedItem();

            main.fxmlPersonenDetailsController.onClickAnzeigeAendernPerson(KvModel.aktuellePerson);

            for (Tab tabPanePersonAnlegen : fxmlPersonenListe.getTabPane().getTabs()) {
                if (tabPanePersonAnlegen.getText().equals("Personen-Details")) {
                    tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);

                }
            }
            //TODO Mohammed

            for (PersonKurs personKurs : KvModel.personKursList) {
                if (personKurs.getKurs().equals("kurs")) {
                    personKurs.getKurs().getKursBeschreibung();

                }
            }
            //TODO
        }
    }


    @FXML
    public void zurucksetzenButtonAction(ActionEvent event) {
        suchTxtField.clear();
        table.getItems();

    }

    private MainController main;

    @FXML
    void suchButtonAction(ActionEvent event) {
        //String such = suchTxtField.getText();
        //System.out.println(such);

        //  filteredData.addAll(list);

//        String searchValue = suchTxtField.getText();
//
//        Predicate<Person> predicate = new Predicate<Person>() {
//
//            @Override
//            public boolean test(Person person) {
//                if (searchValue == null || searchValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare first name and last name of every person with filter text
//                String lowerCaseFilter = searchValue.toLowerCase();
//
//
//                if (person.getVorname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches first name
//                } else if (person.getNachname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name
//                }
//                return false; // Does not match.
//            }
//
//        };
//
//        filteredData.setPredicate(predicate);
    }

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

        kursTeilnahmeStr.setCellValueFactory(person -> new ReadOnlyStringWrapper(KvModel.model.getTeilnehmer(person.getValue()).toString()));


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



        // [Filtering with suchTextField]
        //Wrap the ObserviableList in a FilteredList
        FilteredList<Person> filteredData = new FilteredList<>(KvModel.personList, person -> true);

        // set the filter Predicate whenever the filter changes
        suchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                //if filter text is empty display all persons
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //compare first name and last name...
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getVorname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getAnrede().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getTitel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getNachname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getStrasse().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getPlz().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getOrt().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getTelefon().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });


        //wrap the filterList in a sortedList
        SortedList<Person> sortedData = new SortedList<>(filteredData);

        //bind the SortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        //add sorted and filtered data to the table
        table.setItems(sortedData);
    }

    @FXML
    public void handleFilter() {

    }

    public void init(MainController mainController) {
        main = mainController;
    }


}
