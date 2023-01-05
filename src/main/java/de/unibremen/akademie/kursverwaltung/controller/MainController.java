package de.unibremen.akademie.kursverwaltung.controller;

import javafx.scene.control.Tab;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import javafx.fxml.FXML;

public class MainController {
    @FXML
    KurseDetailsController fxmlKurseDetailsController;
    @FXML
    KurseListeController fxmlKurseListeController;

    @FXML public void initialize(){
        fxmlKurseListeController.init(this);
    }

}