package de.unibremen.akademie.kursverwaltung.domain;

public class Meldung {

    public static void eingabeFehler(String meldung) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Fehler bei der Dateneingabe");
        alert.setHeaderText("Eingabevalidierung");
        alert.setContentText(meldung);
        alert.showAndWait();
    }

}
