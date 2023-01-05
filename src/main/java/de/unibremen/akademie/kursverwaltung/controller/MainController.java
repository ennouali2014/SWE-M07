package de.unibremen.akademie.kursverwaltung.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {
    public TabPane fxmlMain;
    @FXML
    KurseDetailsController fxmlKurseDetailsController;
    @FXML
    KurseListeController fxmlKurseListeController;

    @FXML public void initialize(){
        fxmlKurseListeController.init(this);
    }

}