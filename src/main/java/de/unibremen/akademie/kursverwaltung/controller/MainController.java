package de.unibremen.akademie.kursverwaltung.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {
    public TabPane mainTabPane;

    @FXML
    PersonenDetailsController fxmlPersonenDetailsController;
    @FXML
    PersonenListeController fxmlPersonenListeController;
    @FXML
    KurseDetailsController fxmlKurseDetailsController;
    @FXML
    KurseListeController fxmlKurseListeController;

    @FXML
    public void initialize() {
        fxmlKurseListeController.init(this);
        fxmlPersonenListeController.init(this);
        fxmlPersonenDetailsController.init(this);
        fxmlKurseDetailsController.init(this);
    }


//tabPaneKursAnlegen.getId().equals("fxmlPersonenListe")
}