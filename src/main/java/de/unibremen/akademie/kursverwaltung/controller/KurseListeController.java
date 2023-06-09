

package de.unibremen.akademie.kursverwaltung.controller;


import de.unibremen.akademie.kursverwaltung.application.DatumFormatieren;
import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.KursListe;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import static de.unibremen.akademie.kursverwaltung.domain.AnwendungsModel.kvModel;

public class KurseListeController {


    public TableColumn<Kurs, String> colKursname;
    public TableColumn<Kurs, Date> colStart_Datum;
    public TableColumn<Kurs, Date> colEnd_Datum;
    public TableColumn<Kurs, Integer> colAnzhl_Frei_plaetze;
    public TableColumn<Kurs, Integer> colAnzahl_Teilnehmer;
    public TableColumn<Kurs, String> colStatus;
    @FXML
    public TableColumn<Kurs, String> colKursListTeilnehmerAnKurs;
    @FXML
    public TableColumn<Kurs, String> colKursListInteressentenAnKurs;

    public TableView<Kurs> tableKurseListe;
    public Tab tabKurseListe;
    public Button btnResetAction;
    ObservableList<Kurs> list = FXCollections.observableArrayList();

    @FXML
    private DatePicker pickDatumAb;
    @FXML
    private TableColumn colTeilnehmer;
    @FXML
    private TableColumn colInteressent;

    @FXML
    private ComboBox comboStatusKurseListeSuche;

    @FXML
    private Button btnBearbeiten;

    @FXML
    private DatePicker pickDatumBis;

    @FXML
    private DatePicker pickDate;
    @FXML
    private Button btnEntfernen;
    @FXML
    private Button btnHinzufuegen;
    @FXML
    private TextField txInpSuche;
    @FXML
    private Label lblDatumBis;

    private MainController mainCtrl;
    private FilteredList<Kurs> filteredData;

    public TableView<Kurs> getTableKurseListe() {
        return tableKurseListe;
    }

    public void initialize() {
        DatumFormatieren.datumFormatieren(pickDatumAb);
        DatumFormatieren.datumFormatieren(pickDatumBis);
        pickDatumAb.setPromptText("Startdatum eingeben");
        pickDatumBis.setPromptText("Enddatum eingeben");


        tableKurseListe.setEditable(false);


        tableKurseListe.setPlaceholder(
                new Label("No rows to display"));
        colKursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        colKursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        colStatus.setCellValueFactory(new PropertyValueFactory<Kurs, String>("status"));
        colStatus.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        colAnzhl_Frei_plaetze.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("freiePlaetze"));
        colAnzhl_Frei_plaetze.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());

        colAnzahl_Teilnehmer.setCellValueFactory(new PropertyValueFactory<Kurs, Integer>("aktuelleTnZahl"));
        colAnzahl_Teilnehmer.setCellFactory(ComboBoxTableCell.<Kurs, Integer>forTableColumn());


        colStart_Datum.setCellValueFactory(new PropertyValueFactory<Kurs, Date>("startDatum"));
        //colStart_Datum.setCellFactory(ComboBoxTableCell.<Kurs, String>forTableColumn());
        colStart_Datum.setCellFactory(column -> {
            TableCell<Kurs, Date> cell = new TableCell<Kurs, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });

        colEnd_Datum.setCellValueFactory(new PropertyValueFactory<Kurs, Date>("endeDatum"));
        //colEnd_Datum.setCellFactory(ComboBoxTableCell.<Kurs, String>forTableColumn());
        colEnd_Datum.setCellFactory(column -> {
            TableCell<Kurs, Date> cell = new TableCell<Kurs, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });

        colKursListTeilnehmerAnKurs.setCellValueFactory(kurs -> new ReadOnlyStringWrapper(kvModel.getPkListe().getPersonNameAlsTeilnehmer(kurs.getValue()).toString()));
        colKursListInteressentenAnKurs.setCellValueFactory(kurs -> new ReadOnlyStringWrapper(kvModel.getPkListe().getPersonNameAlsInteressent(kurs.getValue()).toString()));

        tableKurseListe.setItems(kvModel.getKurse().getKursListe());
        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableKurseListe.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.MULTIPLE);


        tableKurseListe.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Kurs> change) -> {
            list = tableKurseListe.getSelectionModel().getSelectedItems();
            btnBearbeiten.setDisable(list != null && list.size() > 1);
        });


        FilteredList<Kurs> filteredData = new FilteredList<>(kvModel.getKurse().getKursListe(), kurs -> true);

        txInpSuche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kurs -> {

                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (kurs.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });


        comboStatusKurseListeSuche.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kurs -> {

                if (newValue == null || newValue.toString().isEmpty() || newValue.toString().isBlank() || newValue.toString().equals("Alle")) {
                    return true;
                }
                String lowerCaseFilter = newValue.toString().toLowerCase();
                if (kurs.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });
//////////////////////////////////////////// TODO //////////////////////////////////////////
        KursListe dateFilter = new KursListe();

        pickDatumAb.valueProperty().addListener((observable, oldValue, newValue) -> {
            dateFilter.setVonDatum(newValue);
            filteredData.setPredicate(kurs -> dateFilter.isBetween(kurs.getStartDatum()));
        });

        pickDatumBis.valueProperty().addListener((observable, oldValue, newValue) -> {
            dateFilter.setBisDatum(newValue);
            dateFilter.alertDatum(dateFilter.getVonDatum(), dateFilter.getBisDatum());
            filteredData.setPredicate(kurs -> dateFilter.isBetween(kurs.getStartDatum()));
        });


        SortedList<Kurs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableKurseListe.comparatorProperty());
        tableKurseListe.setItems(sortedData);

    }

    @FXML
    void onClickHinzufügenButton(ActionEvent event) {
        kvModel.aktuellerKurs = null;
        mainCtrl.fxmlKurseDetailsController.onClickAbbrechenKurs(event);
        for (Tab tabPaneKursAnlegen : tabKurseListe.getTabPane().getTabs()) {
            if (tabPaneKursAnlegen.getText().equals("Kurse-Details")) {
                tabPaneKursAnlegen.getTabPane().getSelectionModel().select(tabPaneKursAnlegen);

            }
        }
    }


    @FXML
    void onClickEntfernenButton(ActionEvent event) {
        //ObservableList<Person> allPerson = kvModel.getPersonen().getPersonenListe();
        List<Kurs> selectedKursCopy = new ArrayList<>(tableKurseListe.getSelectionModel().getSelectedItems());
        selectedKursCopy.forEach(kvModel::removeKurse); // ist das Gleiche wie die folgende Zeile
        // for (Person p : selectedPersonCopy) { kvModel.removePerson(p);}
    }
       /* tableKurseListe.setItems(kvModel.getKurse().getKursListe());
        ObservableList<Kurs> kurse = tableKurseListe.getItems();
        List<Kurs> selectedCoursesCopy = new ArrayList<>(tableKurseListe.getSelectionModel().getSelectedItems());
        selectedCoursesCopy.forEach(kurse::remove);*/


    @FXML
    void abDatselectDate(ActionEvent event) {
        pickDatumAb.getValue();
    }


    @FXML
    void bisDatSelectDate(ActionEvent event) {
        pickDatumBis.getValue();
    }

    @FXML
    void onClickcomboStatusKurseListeSelect(ActionEvent event) {
        comboStatusKurseListeSuche.getValue();
    }

    @FXML
    void onClickBearbeitenButton(ActionEvent event) {
        tableKurseListe.setItems(kvModel.getKurse().getKursListe());
        if (!tableKurseListe.getSelectionModel().isEmpty() && tableKurseListe.getSelectionModel().getSelectedItems().size() < 2) {
            kvModel.aktuellerKurs = tableKurseListe.getSelectionModel().getSelectedItem();
            //KvModel.aktuellerKurs = tableView.getSelectionModel().;

            mainCtrl.fxmlKurseDetailsController.anzeigeZumAendernKurs(kvModel.aktuellerKurs);
            mainCtrl.fxmlKurseDetailsController.show();


        }
    }


    public void searchButtonAction(ActionEvent actionEvent) {

        String searchText = txInpSuche.getText();
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


    public void personAnlegenButtonAction(ActionEvent actionEvent) {
    }

    public void init(MainController mainController) {
        mainCtrl = mainController;
    }


    public void resetButtonAction(ActionEvent actionEvent) {
        txInpSuche.clear();
        pickDatumAb.setValue(null);
        pickDatumBis.setValue(null);
        comboStatusKurseListeSuche.setValue("");

        // tableKurseListe.getItems();
    }
}
