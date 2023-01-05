package de.unibremen.akademie.kursverwaltung.controller;


import de.unibremen.akademie.kursverwaltung.domain.Anrede;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PersonenListeController implements Initializable {

    public Tab ContentPersonenListe;
    @FXML
    private TableView<Person> table;


    @FXML
    private TableColumn<Anrede, Enum> anrede;

    @FXML
    private TableColumn<Person, String> vorname;

    @FXML
    private TableColumn<Person, String> name;

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
    private CheckBox interesseneChoiceBox;

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
    private CheckBox teilnehmerChoiceBox;

    @FXML
    private Button zurucksetzenButton;

    public TableColumn columnSelect;

    @FXML
    public void andernButtonAction(ActionEvent event) {

        //table.setEditable(true);
        //ObservableList<Person> selectedPerson = table.getSelectionModel().getSelectedItems();
        //System.out.println("Ausgewählte Person" + selectedPerson);
        //selectedPerson = table.getItems();
    }

    @FXML
    public void loeschButtonAction(ActionEvent event) {
        ObservableList<Person> allPerson = table.getItems();
        List<Person> selectedPersonCopy = new ArrayList<>(table.getSelectionModel().getSelectedItems());
        selectedPersonCopy.forEach(allPerson::remove);

    }

    @FXML
    public void personAnlegenButtonAction(ActionEvent event) {
        for (Tab tabPanePersonAnlegen : ContentPersonenListe.getTabPane().getTabs()) {
            if (tabPanePersonAnlegen.getText().equals("Personen-Details")) {
                tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);
            }
        }
    }

    @FXML
    void suchButtonAction(ActionEvent event) {

    }

    @FXML
    public void zurucksetzenButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     /*   anrede.setCellValueFactory(new PropertyValueFactory<Anrede, Enum >("anrede"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        vorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        strasse.setCellValueFactory(new PropertyValueFactory<Person, String>("strasse"));
        plz.setCellValueFactory(new PropertyValueFactory<Person, String>("plz"));
        ort.setCellValueFactory(new PropertyValueFactory<Person, String>("ort"));
        email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        telefon.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));

       // table.setItems(list);

*/
        // table.setEditable(false);

        // table.setPlaceholder(
        //         new Label("No rows to display"));
        // columnSelect.setGraphic(new CheckBox());
        //columnSelect.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().getSelect()));
        //  columnSelect.setCellFactory(CheckBoxTableCell.<Person>forTableColumn(columnSelect));

        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        name.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        vorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        vorname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        strasse.setCellValueFactory(new PropertyValueFactory<Person, String>("strasse"));
        strasse.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn());

        plz.setCellValueFactory(new PropertyValueFactory<Person, String>("plz"));
        plz.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn());

        ort.setCellValueFactory(new PropertyValueFactory<Person, String>("ort"));
        ort.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn());

        email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        email.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn());

        telefon.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));
        telefon.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn());
        table.setItems(KvModel.model.personList);

        TableView.TableViewSelectionModel<Person> selectionModel =
                table.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.MULTIPLE);
    }
}


