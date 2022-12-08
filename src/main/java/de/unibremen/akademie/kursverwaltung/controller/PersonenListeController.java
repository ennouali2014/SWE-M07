package de.unibremen.akademie.kursverwaltung.controller;


import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

        //table column methode
    }
}


