package de.unibremen.akademie.kursverwaltung.controller;


import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class PersonenListeController implements Initializable {

    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> anrede;

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
    private TableColumn<Person,Boolean> alleCheckBox;

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
    void andernButtonAction(ActionEvent event) {

    }

    @FXML
    void loeschButtonAction(ActionEvent event) {

        ObservableList<Person> allPerson, singlePerson;
        allPerson = table.getItems();
        singlePerson = table.getSelectionModel().getSelectedItems();
        singlePerson.forEach(allPerson::remove);

    }

    @FXML
    void personAnlegenButtonAction(ActionEvent event) {

    }

    @FXML
    void suchButtonAction(ActionEvent event) {

    }

    @FXML
    void zurucksetzenButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nicht löschen - that is an example to see if the cod works or not
        //table column methode

        //ObservableList<Person> list = FXCollections.observableArrayList(
          //      new Person("Daniel","xx"),
        //      new Person("xx","xx"));


        //name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        //vorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
        //table.setItems(list);

    }
}


