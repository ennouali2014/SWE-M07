package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.Kursverwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Date;

public class KurseListeController {


    public TableColumn<Kurs, Date> columnStartDatum;
    public TableColumn<Kurs, Date> columnEndDatum;
    public TableColumn<Kurs, Integer> columnAnzFreiPlz;
    public TableColumn<Kurs, Integer> columnAnzTeilnehm;
    public TableColumn<Kurs, String> columnStatus;
    public TableColumn<Kurs, String> columnName;
    public TableView<Kurs> tableView;
    public TableColumn columnSelect;
    public Tab ContentKurseListe;


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

        for (Tab tabPaneKursAnlegen : ContentKurseListe.getTabPane().getTabs()) {
            if (tabPaneKursAnlegen.getText().equals("Kurse-Details")) {
                tabPaneKursAnlegen.getTabPane().getSelectionModel().select(tabPaneKursAnlegen);
            }
        }
    }


    @FXML
    void bisDatSelectDate(ActionEvent event) {

    }


    @FXML
    void entfernenButtonAction(ActionEvent event) {

    }

    @FXML
    void hinzufugenButtonAction(ActionEvent event) {

        for (Tab tabPaneKursAnlegen : ContentKurseListe.getTabPane().getTabs()) {
            if (tabPaneKursAnlegen.getText().equals("Kurse-Details")) {
                tabPaneKursAnlegen.getTabPane().getSelectionModel().select(tabPaneKursAnlegen);
            }

        }
    }

    public void initialize() {
        tableView.setEditable(false);

        tableView.setPlaceholder(
                new Label("No rows to display"));
        columnSelect.setGraphic(new CheckBox());
        //columnSelect.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().getSelect()));
        columnSelect.setCellFactory(CheckBoxTableCell.<Kurs>forTableColumn(columnSelect));

        columnName.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        columnName.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        columnStatus.setCellValueFactory(new PropertyValueFactory<Kurs, String>("status"));
        columnStatus.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        columnAnzFreiPlz.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("freiePlaetze"));
        columnAnzFreiPlz.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());

        columnAnzTeilnehm.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("aktuelleTnZahl"));
        columnAnzTeilnehm.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());

        columnStartDatum.setCellValueFactory(new PropertyValueFactory<Kurs, Date>("startDatum"));
        columnStartDatum.setCellFactory(ComboBoxTableCell.<Kurs, Date>forTableColumn());

        columnEndDatum.setCellValueFactory(new PropertyValueFactory<Kurs, Date>("endeDatum"));
        columnEndDatum.setCellFactory(ComboBoxTableCell.<Kurs, Date>forTableColumn());

        tableView.setItems(Kursverwaltung.model.kursList);
        //System.out.println(parseKursList().get(2).getStartDatum());
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int number = tableView.getSelectionModel().getSelectedItems().size();
            }
        });

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
