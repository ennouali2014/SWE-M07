package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PersonenDetailsController {
    @FXML
    //public TextField anrede;
    public TextField titel;
    public TextField name;
    public TextField vorname;
    public TextField strasse;
    public TextField plz;
    public TextField email;
    public TextField telefon;
    public TextField ort;
    public ChoiceBox anrede;
    public Button save;
    public String nameS;
    public ObservableList<String> choiceList = FXCollections.observableArrayList();
    public void initialize() {

        choiceList.add("Herr");
        choiceList.add("Frau");
        choiceList.add("Divers");
        choiceList.add(" ");


        anrede.setItems(choiceList);
    }


    @FXML
    public void onsaveclick() {

        KvModel.model.addPerson(anrede.getValue().toString(), titel.getText(), name.getText(), vorname.getText(), strasse.getText(), plz.getText(), ort.getText(), email.getText(), telefon.getText());
        titel.clear();
        name.clear();
        vorname.clear();
        strasse.clear();
        plz.clear();
        ort.clear();
        email.clear();
        telefon.clear();
        System.out.println(KvModel.model.personList);


    }


}
