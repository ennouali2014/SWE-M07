
package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Kursverwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Date;
import java.util.List;

public class KurseListeController {


    public TableColumn<Kurs,Date> columnStartDatum;
    public TableColumn<Kurs, Date> columnEndDatum;
    public TableColumn<Kurs,Integer> columnAnzFreiPlz;
    public TableColumn<Kurs,Integer> columnAnzTeilnehm;
    public TableColumn<Kurs,String> columnStatus;
    public TableColumn<Kurs,String> columnName;
    public TableView<Kurs> tableView;


    @FXML
    private DatePicker abDatumDatePicker;


    @FXML
    private MenuButton alleMenuButton;

    @FXML
    private Button bearbeitenButton;

    @FXML
    private DatePicker bisDatumDatePicker;

    @FXML
    private DatePicker datePicker1;

    @FXML
    private Button entfernenButton;

    @FXML
    private Button hinzufugenButton;

    @FXML
    private TextField kursNameTextField;

    @FXML
    private Label lblAbDatum;


    @FXML
    private Label lblBisDatum;

    @FXML
    private Label lblTextField;

    @FXML
    void abDatselectDate(ActionEvent event) {

        lblBisDatum.setText(datePicker1.getValue().toString());

    }

    @FXML
    void bearbeitenButtonAction(ActionEvent event) {

    }

    @FXML
    void bisDatSelectDate(ActionEvent event) {

    }


    @FXML
    void entfernenButtonAction(ActionEvent event) {

    }

    @FXML
    void hinzufugenButtonAction(ActionEvent event) {

    }

    public void initialize() {
        tableView.setEditable(true);

        tableView.setPlaceholder(
                new Label("No rows to display"));

        columnName.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        columnName.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        columnStatus.setCellValueFactory(new PropertyValueFactory<Kurs, String>("status"));
        columnStatus.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        //columnAnzFreiPlz.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("freiePlaetze"));
        //columnAnzFreiPlz.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());

        //columnAnzTeilnehm.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("anzTeilnehm"));
        //columnAnzTeilnehm.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());

        //columnStartDatum.setCellValueFactory(new PropertyValueFactory<Kurs, Date>("startDatum"));
        //columnStartDatum.setCellFactory(ComboBoxTableCell.<Kurs, Date>forTableColumn());

        //columnEndDatum.setCellValueFactory(new PropertyValueFactory<Kurs, Date>("endDatum"));
        //columnEndDatum.setCellFactory(ComboBoxTableCell.<Kurs, Date>forTableColumn());

        tableView.getItems().setAll(parseKursList());

        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableView.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.MULTIPLE);
    }
    private List<Kurs> parseKursList(){
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        Kursverwaltung kursverwaltung= new Kursverwaltung();
        kursverwaltung.addnewKurs("php",12,3,new Date(1672963200000L),12,2,150,19,"php backend");
        kursverwaltung.addnewKurs("java",12,3,new Date(1672963200000L),12,2,150,19,"php backend");
        kursverwaltung.addnewKurs("arduino",10,3,new Date(1569852800000L),12,2,150,19,"php backend");

        return kursverwaltung.getKursList();
    }

    public void suchButtonAction(ActionEvent actionEvent) {
    }

    public void zurucksetzenButtonAction(ActionEvent actionEvent) {
    }

    public void personAnlegenButtonAction(ActionEvent actionEvent) {
    }

    public void andernButtonAction(ActionEvent actionEvent) {
    }

    public void loeschButtonAction(ActionEvent actionEvent) {
    }
}
