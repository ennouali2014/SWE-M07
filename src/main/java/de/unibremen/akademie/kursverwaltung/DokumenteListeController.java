package de.unibremen.akademie.kursverwaltung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class DokumenteListeController {

    @FXML
    private Label welcomeText;

    @FXML
    private TreeView bausteinList;

    @FXML
    protected void displayList(ActionEvent event) {

        TreeItem textbausteinList = new TreeItem("Textbausteine List");
        TreeItem email = new TreeItem("email");
        TreeItem message = new TreeItem("Message");
        TreeItem certificate = new TreeItem("Certificate");
        TreeItem warning = new TreeItem("Warning");
        TreeItem invoice = new TreeItem("Invoice");


        TreeItem email1 = new TreeItem("email 1");
        TreeItem email2 = new TreeItem("email 2");
        TreeItem message1 = new TreeItem("Message 1");
        TreeItem message2 = new TreeItem("Message 2");
        TreeItem certificat1 = new TreeItem("Certificate 1");
        TreeItem warning1 = new TreeItem("Warning 1");
        TreeItem invoice1 = new TreeItem("Invoice 1");

        bausteinList.setRoot(textbausteinList);
        textbausteinList.getChildren().addAll(email, message, certificate, warning, invoice);

        email.getChildren().addAll(email1, email2);

        message.getChildren().addAll(message1, message2);

        certificate.getChildren().addAll(certificat1);

        warning.getChildren().addAll(warning1);

        invoice.getChildren().addAll(invoice1);

    }
}
