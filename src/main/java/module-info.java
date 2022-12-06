module de.unibremen.akademie.kursverwaltung {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.unibremen.akademie.kursverwaltung to javafx.fxml;
    exports de.unibremen.akademie.kursverwaltung;
}