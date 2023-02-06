package de.unibremen.akademie.kursverwaltung.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

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
    Stage mainStage;

    @FXML
    public void initialize() {
        fxmlKurseListeController.init(this);
        fxmlPersonenListeController.init(this);
        fxmlPersonenDetailsController.init(this);
        fxmlKurseDetailsController.init(this);
    }


//tabPaneKursAnlegen.getId().equals("fxmlPersonenListe")
}