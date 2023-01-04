package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PersonenDetailsController {
    @FXML
    public ChoiceBox anrede;
    @FXML
    public TextField titel;
    @FXML
    public TextField name;
    @FXML
    public TextField vorname;
    @FXML
    public TextField strasse;
    @FXML
    public TextField plz;
    @FXML
    public TextField email;
    @FXML
    public TextField telefon;
    @FXML
    public TextField ort;
    @FXML
    public Button save;

    public ObservableList<String> choiceListAnrede = FXCollections.observableArrayList();

    public void initialize() {
        choiceListAnrede.add("");
        choiceListAnrede.add("Herr");
        choiceListAnrede.add("Frau");
        choiceListAnrede.add("Divers");
        anrede.setItems(choiceListAnrede);
        anrede.getSelectionModel().selectFirst();
    }

    @FXML
    public void onsaveclick() {
        Person person = Person.addNewPerson(anrede.getValue().toString(), titel.getText(), name.getText(), vorname.getText(), strasse.getText(), plz.getText(), ort.getText(), email.getText(), telefon.getText());
        anrede.getSelectionModel().selectFirst();
        titel.clear();
        name.clear();
        vorname.clear();
        strasse.clear();
        plz.clear();
        ort.clear();
        email.clear();
        telefon.clear();
        //System.out.println(KvModel.personList);
    }
}
