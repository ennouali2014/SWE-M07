package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

public class PersonenListeController implements Initializable {
    @FXML
    public Tab tabPersonenListe;
    @FXML
    public TableView<Person> tablePersonenListe;
    @FXML
    public TableColumn<Person, String> colPersonenListeTeilnahmeKurse;
    @FXML
    public TableColumn<Person, String> colPersonenListeInteressierteKurse;
    @FXML
    private TableColumn<Person, String> colPersonenListeAnrede;
    // TODO wird noch bearbeitet! Mohammed
    String listPersonDetails[] = {"titel", "vorname", "nachname", "strasse", "plz", "ort", "email", "telefon"};
    @FXML
    private TableColumn<Person, String> colPersonenListeVorname;
    @FXML
    private TableColumn<Person, String> colPersonenListeNachname;
    @FXML
    private TableColumn<Person, String> colPersonenListeTitel;
    @FXML
    private TableColumn<Person, String> colPersonenListeStrasse;
    @FXML
    private TableColumn<Person, String> colPersonenListePlz;
    @FXML
    private TableColumn<Person, String> colPersonenListeOrt;
    @FXML
    private TableColumn<Person, String> colPersonenListeEmail;
    @FXML
    private TableColumn<Person, String> colPersonenListeTelefon;
    @FXML
    private Button btnAendernAnzeigen;
    @FXML
    private Button btnPersonAusListeLoeschen;
    @FXML
    private Button btnPersonAnlegenPersonenListe;
    @FXML
    private TextField txInpPersonSuche;
    private ObservableList<Person> list = FXCollections.observableArrayList();
    @FXML
    private Button btnResetSuchfeld;

    private FilteredList<Person> filteredData;
    private MainController main;

    @FXML

    public void onClickPersonAusListeLoeschen(ActionEvent event) {
        ObservableList<Person> allPerson = kvModel.getPersonen().getPersonenListe();
        List<Person> selectedPersonCopy = new ArrayList<>(tablePersonenListe.getSelectionModel().getSelectedItems());
        selectedPersonCopy.forEach(allPerson::remove);
    }

    @FXML
    public void onClickPersonAnlegenPersonenListe(ActionEvent event) {

        AnwendungsModel.aktuellePerson = null;
        AnwendungsModel.aktuellePerson = null;
        main.fxmlPersonenDetailsController.felderLeeren();
        PersonenDetailsController.zurueckPersonenliste = true;
        for (Tab tabPanePersonAnlegen : tabPersonenListe.getTabPane().getTabs()) {
            if (tabPanePersonAnlegen.getText().equals("Personen-Details")) {
                tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);
            }
        }
    }

    @FXML
    public void onClickPersonAendernPersonenListe(ActionEvent event) {
        PersonenDetailsController.zurueckPersonenliste = true;

        if (!tablePersonenListe.getSelectionModel().isEmpty()) {
            main.fxmlPersonenDetailsController.btnSavePersonDetails.setText("Update");

            AnwendungsModel.aktuellePerson = tablePersonenListe.getSelectionModel().getSelectedItem();

            main.fxmlPersonenDetailsController.onClickAnzeigeAendernPerson(AnwendungsModel.aktuellePerson);

            for (Tab tabPanePersonAnlegen : tabPersonenListe.getTabPane().getTabs()) {
                if (tabPanePersonAnlegen.getText().equals("Personen-Details")) {
                    tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);

                }
            }
            //TODO Mohammed

//            for (PersonKurs personKurs : pkListe.personKursList) {
//                if (personKurs.getKurs().equals("kurs")) {
//                    personKurs.getKurs().getTeilnehmerListe();
//                }
//            }
            //TODO
        }
    }


    @FXML
    public void onClickResetSuchfeld(ActionEvent event) {
        txInpPersonSuche.clear();
        tablePersonenListe.getItems();

    }



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
        tablePersonenListe.setEditable(true);
        colPersonenListeAnrede.setCellValueFactory(new PropertyValueFactory<Person, String>("anrede"));
        colPersonenListeAnrede.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn("", "Herr", "Frau", "Divers"));
        colPersonenListeAnrede.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAnrede(t.getNewValue());
                    }
                }
        );
        colPersonenListeTitel.setCellValueFactory(new PropertyValueFactory<Person, String>("titel"));
        colPersonenListeTitel.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeTitel.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setTitel(v.getNewValue());
                    }
                }
        );

        colPersonenListeVorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        colPersonenListeVorname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeVorname.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setVorname(v.getNewValue());
                    }
                }
        );

        colPersonenListeNachname.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
        colPersonenListeNachname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeNachname.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setNachname(v.getNewValue());
                    }
                }
        );


        colPersonenListeStrasse.setCellValueFactory(new PropertyValueFactory<Person, String>("strasse"));
        colPersonenListeStrasse.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeStrasse.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setStrasse(v.getNewValue());
                    }
                }
        );

        colPersonenListePlz.setCellValueFactory(new PropertyValueFactory<Person, String>("plz"));
        colPersonenListePlz.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListePlz.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setPlz(v.getNewValue());
                    }
                }
        );

        colPersonenListeOrt.setCellValueFactory(new PropertyValueFactory<Person, String>("ort"));
        colPersonenListeOrt.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeOrt.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setOrt(v.getNewValue());
                    }
                }
        );

        colPersonenListeEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        colPersonenListeEmail.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeEmail.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setTelefon(v.getNewValue());
                    }
                }

        );

        colPersonenListeTelefon.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));
        colPersonenListeTelefon.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        colPersonenListeTelefon.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> v) {
                        ((Person) v.getTableView().getItems().get(
                                v.getTablePosition().getRow())
                        ).setTelefon(v.getNewValue());
                    }
                }
        );

        colPersonenListeTeilnahmeKurse.setCellValueFactory(person -> new ReadOnlyStringWrapper(kvModel.getPkListe().getKurseAlsTeilnehmer(person.getValue()).toString()));
        colPersonenListeInteressierteKurse.setCellValueFactory(person -> new ReadOnlyStringWrapper(kvModel.getPkListe().getKurseAlsInteressent(person.getValue()).toString()));


        tablePersonenListe.setItems(kvModel.getPersonen().getPersonenListe());

        TableView.TableViewSelectionModel<Person> selectionModel = tablePersonenListe.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        tablePersonenListe.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Person> change) -> {
            list = tablePersonenListe.getSelectionModel().getSelectedItems();
            btnAendernAnzeigen.setDisable(list != null && list.size() > 1);
        });



        // [Filtering with suchTextField]
        //Wrap the ObserviableList in a FilteredList
        FilteredList<Person> filteredData = new FilteredList<>(kvModel.getPersonen().getPersonenListe(), person -> true);

        // set the filter Predicate whenever the filter changes
        txInpPersonSuche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                //if filter text is empty display all persons
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //compare first name and last name...
                String lowerCaseFilter = newValue.toLowerCase();
                 if (person.getAnrede().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getTitel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getVorname().toLowerCase().contains(lowerCaseFilter)) {
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
        sortedData.comparatorProperty().bind(tablePersonenListe.comparatorProperty());

        //add sorted and filtered data to the table
        tablePersonenListe.setItems(sortedData);
    }

    @FXML
    public void handleFilter() {

    }

    public void init(MainController mainController) {
        main = mainController;
    }


}
