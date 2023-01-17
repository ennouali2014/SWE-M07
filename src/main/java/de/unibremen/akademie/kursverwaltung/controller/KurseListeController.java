package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class KurseListeController {


    public TableColumn<Kurs, String> columnStartDatum;
    public TableColumn<Kurs, String> columnEndDatum;
    public TableColumn<Kurs, Integer> columnAnzFreiPlz;
    public TableColumn<Kurs, Integer> columnAnzTeilnehm;
    public TableColumn<Kurs, String> columnStatus;
    public TableColumn<Kurs, String> columnName;
    public TableView<Kurs> tableView;
    public TableColumn columnSelect;
    public Tab fxmlKurseListe;
    public CheckBox checkbox;


    ObservableList<Kurs> list = FXCollections.observableArrayList();

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
    private MainController main;
    private FilteredList<Kurs> filteredData;

    public TableView<Kurs> getTableView() {
        return tableView;
    }

    public void initialize() {


        tableView.setEditable(false);


        tableView.setPlaceholder(
                new Label("No rows to display"));
        columnName.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        columnName.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        columnStatus.setCellValueFactory(new PropertyValueFactory<Kurs, String>("status"));
        columnStatus.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        columnAnzFreiPlz.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("freiePlaetze"));
        columnAnzFreiPlz.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());

        columnAnzTeilnehm.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("aktuelleTnZahl"));
        columnAnzTeilnehm.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());


        columnStartDatum.setCellValueFactory(new PropertyValueFactory<Kurs, String>("displaystartDate"));
        columnStartDatum.setCellFactory(ComboBoxTableCell.<Kurs, String>forTableColumn());

        columnEndDatum.setCellValueFactory(new PropertyValueFactory<Kurs, String>("displayEndeDate"));
        columnEndDatum.setCellFactory(ComboBoxTableCell.<Kurs, String>forTableColumn());

        tableView.setItems(KvModel.model.kursList);
        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableView.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.MULTIPLE);


        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                list = tableView.getSelectionModel().getSelectedItems();
            }
            if (list.size() > 1) {
                bearbeitenButton.setDisable(true);
            } else {
                bearbeitenButton.setDisable(false);
            }
        });

        FilteredList<Kurs> filteredData = new FilteredList<>(KvModel.kursList, kurs -> true);
        kursNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kurs -> {

                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (kurs.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (kurs.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Kurs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    @FXML
    void hinzufugenButtonAction(ActionEvent event) {
        if (!tableView.getSelectionModel().isEmpty() && tableView.getSelectionModel().getSelectedItems().size() < 2) {
            KvModel.aktuelleKurs = null;
            main.fxmlKurseDetailsController.abbrechen(event);
            for (Tab tabPaneKursAnlegen : fxmlKurseListe.getTabPane().getTabs()) {
                if (tabPaneKursAnlegen.getText().equals("Kurse-Details")) {
                    tabPaneKursAnlegen.getTabPane().getSelectionModel().select(tabPaneKursAnlegen);

                }
            }
        }
    }

    @FXML
    void entfernenButtonAction(ActionEvent event) {
        tableView.setItems(KvModel.model.kursList);
        ObservableList<Kurs> kurse = tableView.getItems();
        List<Kurs> selectedCoursesCopy = new ArrayList<>(tableView.getSelectionModel().getSelectedItems());
        selectedCoursesCopy.forEach(kurse::remove);
    }

    @FXML
    void abDatselectDate(ActionEvent event) {

        lblBisDatum.setText(datePicker1.getValue().toString());
    }

    @FXML
    void bearbeitenButtonAction(ActionEvent event) {
        tableView.setItems(KvModel.model.kursList);
        if (!tableView.getSelectionModel().isEmpty() && tableView.getSelectionModel().getSelectedItems().size() < 2) {
            KvModel.aktuelleKurs = tableView.getSelectionModel().getSelectedItem();
            main.fxmlKurseDetailsController.update(KvModel.aktuelleKurs);
            main.fxmlKurseDetailsController.show();
        }
    }


    @FXML
    void bisDatSelectDate(ActionEvent event) {

    }


    public void searchButtonAction(ActionEvent actionEvent) {

        String searchText = kursNameTextField.getText();
        Predicate<Kurs> predicate = new Predicate<Kurs>() {
            @Override
            public boolean test(Kurs kurs) {
                if (searchText == null || searchText.toLowerCase().isEmpty() || searchText.isBlank()) {
                    return true;
                }
                String searchKeywordLowerC = searchText.toLowerCase();
                return kurs.getName().toLowerCase().contains(searchKeywordLowerC);
            }
        };
        filteredData.setPredicate(predicate);
    }


    public void suchButtonAction(ActionEvent actionEvent) {


    }

    public void zurucksetzenButtonAction(ActionEvent actionEvent) {
    }

    public void personAnlegenButtonAction(ActionEvent actionEvent) {
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public void allselect(ActionEvent actionEvent) {
        System.out.println(checkbox.isIndeterminate());
        if (checkbox.isIndeterminate()) {
            System.out.println(checkbox.isIndeterminate());
            tableView.getSelectionModel().selectAll();
        }
    }
}
