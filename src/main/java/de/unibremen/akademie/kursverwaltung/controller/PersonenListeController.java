package de.unibremen.akademie.kursverwaltung.controller;


import de.unibremen.akademie.kursverwaltung.domain.Anrede;
import de.unibremen.akademie.kursverwaltung.domain.Kursverwaltung;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PersonenListeController implements Initializable {

    @FXML
    private TableView<Person> table;


    @FXML
    private TableColumn<Anrede, Enum> anrede;

    @FXML
    private TableColumn<Person, String> vorname;

    @FXML
    private TableColumn<Person, String> name;

    @FXML
    private TableColumn<Person, Boolean> bezahlt;

    @FXML
    private TableColumn<Person, Boolean> informiert;

    @FXML
    private TableColumn<Person, Boolean> zertifikat;
    @FXML
    private TableColumn<Person, String> teilnehmer;

    @FXML
    private TableColumn<Person, String> interessent;

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


    @FXML
    public void andernButtonAction(ActionEvent event) {

    }

    @FXML
    public void loeschButtonAction(ActionEvent event) {

        ObservableList<Person> allPerson, singlePerson;
        allPerson = table.getItems();
        singlePerson = table.getSelectionModel().getSelectedItems();
        singlePerson.forEach(allPerson::remove);

    }

    @FXML
    public void personAnlegenButtonAction(ActionEvent event) {

    }

    @FXML
    void suchButtonAction(ActionEvent event) {

    }

    @FXML
    public void zurucksetzenButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nicht löschen - that is an example to see if the cod works or not
        //table column methode

        //ObservableList<Person> list = FXCollections.observableArrayList(
        //      new Person("Daniel","xx"),
        //      new Person("xx","xx"));


      /*  anrede.setCellValueFactory(new PropertyValueFactory<Anrede, Enum >("anrede"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        vorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));

        //table.setItems(list);

*/
    }

    private List<Person> parsePersonList() {
        Kursverwaltung kursverwaltung = new Kursverwaltung();
        kursverwaltung.addPerson(Anrede.FRAU, "Daniela", "Sally", "Louis str.", "28355", "Bremen", "oz.t@mail.com", "0144441154");
        kursverwaltung.addPerson(Anrede.HERR, "Daniel", "John", "Lili str.", "28000", "Bremen", "dan@mail.com", "04511121");
        kursverwaltung.addPerson(Anrede.FRAU, "Georgia", "Cindy", " Mimosa str.", "28355", "Bremen", "mim.t@mail.com", "0144441154");
        kursverwaltung.addPerson(Anrede.HERR, "Ludwig", "Johan", "Moon str.", "28000", "Bremen", "johan@mail.com", "04511121");


        return kursverwaltung.getPersonList();
    }
}


