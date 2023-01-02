package de.unibremen.akademie.kursverwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        // Daten laden
        stage.setTitle("Kursverwaltung v 1.0");
        stage.setScene(scene);
        stage.show();
        // Daten speichern
    }

    public static void main(String[] args) {
        launch();
    }
}