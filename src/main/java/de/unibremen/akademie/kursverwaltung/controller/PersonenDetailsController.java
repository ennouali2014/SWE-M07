package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class PersonenDetailsController {
    @FXML
    public ChoiceBox anrede;

    @FXML
    public TextField titel;
    @FXML
    public TextField vorname;
    @FXML
    public TextField nachname;
    @FXML
    public TextField strasse;
    @FXML
    public TextField plz;
    @FXML
    public TextField email;
    @FXML
    public TextField telefon;
    @FXML
    public TextField ort;
    @FXML
    public Button save;

    @FXML
    public Button abbrechen;
    @FXML
    public ObservableList<String> choiceListAnrede = FXCollections.observableArrayList();
    static public final ObservableList<Person> listKursTeilnehmer =
            FXCollections.observableArrayList();
    static public final ObservableList<Person> listKursInteressent =
            FXCollections.observableArrayList();
    @FXML
    public Tab fxmlPersonenDetails;

    static public boolean zurueckPersonenliste = false;
    static public boolean bearbeiten = false;
    @FXML
    public TableView tableViewKurse;
    @FXML
    public Label kursliste;
    @FXML
    public TableColumn kursname;
    public TableColumn startDate;
    @FXML
    public TableColumn status;
    @FXML
    public TableColumn kursZuTeilnehmer;
    @FXML
    public TableView tableViewTeilnehmerZu;
    @FXML
    public TableView tableViewInteressentenZu;
    @FXML
    public TableColumn kursZuInteressent;
    @FXML
    public Button zuTeilnehmer;
    @FXML
    public Button zuInteressent;
    @FXML
    public Button loeschVonInteressenten;
    @FXML
    public Button kurs_interessent;
    @FXML
    public Button loeschVonTeilnehmer;
    @FXML
    public Button kurs_teilnehmer;
    public TableView TableViewKurse;


    private MainController main;

    public void init(MainController mainController) {
        main = mainController;
    }


    public void initialize() {
        choiceListAnrede.add("");
        choiceListAnrede.add("Herr");
        choiceListAnrede.add("Frau");
        choiceListAnrede.add("Divers");
        anrede.setItems(choiceListAnrede);
        anrede.getSelectionModel().selectFirst();
        kursname.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        kursname.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());
        startDate.setCellValueFactory(new PropertyValueFactory<Kurs, String>("displaystartDate"));
        startDate.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());


        tableViewKurse.setItems(KvModel.model.kursList);
        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableViewKurse.getSelectionModel();


        tableViewKurse.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        kursZuTeilnehmer.setCellValueFactory(new PropertyValueFactory<Kurs, String>("name"));
        kursZuTeilnehmer.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());
//        tableViewKurse.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> System.out.println(newValue));

        
    }

    @FXML
    public void onClickSavePerson() {
        Person person = null;
        // Update einer bestehenden Person
        if (KvModel.aktuellePerson != null) {
            try {
                KvModel.aktuellePerson.updatePerson(anrede.getValue().toString(), titel.getText(), vorname.getText(), nachname.getText(), strasse.getText(), plz.getText(), ort.getText(), email.getText(), telefon.getText());
                person = KvModel.aktuellePerson;
                KvModel.personKursList.addAll(PersonKursListe.personKursList);
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            felderLeeren();
            save.setText("speichern");
        } else {
            // Neue Person hinzufuegen
            int aktuelleAnzPersonen = KvModel.personList.size();
            try {
                person = Person.addNewPerson(anrede.getValue().toString(), titel.getText(), vorname.getText(), nachname.getText(), strasse.getText(), plz.getText(), ort.getText(), email.getText(), telefon.getText());
            } catch (Exception e) {
                Meldung.eingabeFehler(e.getMessage());
                return;
            }
            if (KvModel.personList.size() > aktuelleAnzPersonen) {
                felderLeeren();
            }
        }
        KvModel.aktuellePerson = null;
        Tab plTab = main.fxmlPersonenListeController.fxmlPersonenListe;
        //plTab.getTabPane().getSelectionModel().select(plTab);
        main.fxmlPersonenListeController.table.refresh();

        if (PersonenDetailsController.zurueckPersonenliste) {
            plTab.getTabPane().getSelectionModel().select(plTab);

            main.fxmlPersonenListeController.table.getSelectionModel().clearSelection();
            main.fxmlPersonenListeController.table.getSelectionModel().select(person);
        }
    }

    @FXML
    public void onClickAnzeigeAendernPerson(Person person) {
        if (person != null) {
            String anrede = person.getAnrede();
            for (int i = 0; i < choiceListAnrede.size(); i++) {
                if (choiceListAnrede.get(i).equals(anrede)) {
                    this.anrede.getSelectionModel().select(i);
                }
            }
            this.titel.setText(person.getTitel());
            this.vorname.setText(person.getVorname());
            this.nachname.setText(person.getNachname());
            this.strasse.setText(person.getStrasse());
            this.plz.setText(person.getPlz());
            this.ort.setText(person.getOrt());
            this.email.setText(person.getEmail());
            this.telefon.setText(person.getTelefon());
        }
    }

    @FXML
    public void onClickAbbrechenPerson(ActionEvent event) {
        felderLeeren();

        if (zurueckPersonenliste) {
            //System.out.println(zurueckPersonenliste);
            Tab plTab = main.fxmlPersonenListeController.fxmlPersonenListe;
            plTab.getTabPane().getSelectionModel().select(plTab);
            zurueckPersonenliste = false;
        }
    }

    public void felderLeeren() {
        anrede.getSelectionModel().selectFirst();
        titel.clear();
        vorname.clear();
        nachname.clear();
        strasse.clear();
        plz.clear();
        ort.clear();
        email.clear();
        telefon.clear();
        tableViewTeilnehmerZu.getItems().clear();
        save.setText("speichern");
    }

    public void teilnehmerZuInteressent(ActionEvent actionEvent) {
    }

    public void interessentZuTeilnehmer(ActionEvent actionEvent) {
    }

    public void AusInteressentRaus(ActionEvent actionEvent) {
    }

    public void ausTeilnehmerRaus(ActionEvent actionEvent) {
        tableViewTeilnehmerZu.getItems().removeAll(tableViewTeilnehmerZu.getSelectionModel().getSelectedItem());
    }

    /*
        public void kursZuTeilnehmer(ActionEvent actionEvent) {

            if (KvModel.aktuellePerson == null || tableViewKurse.getSelectionModel().getSelectedItem() == null) {
                return;
            }

            Kurs kurs = (Kurs) tableViewKurse.getSelectionModel().getSelectedItem();
            KvModel.aktuellePerson.addKursTeilnehmer(kurs); //
            tableViewTeilnehmerZu.getItems().add(kurs);

            System.out.println(KvModel.aktuellePerson);
            System.out.println(tableViewKurse.getSelectionModel().getSelectedItem());


        }
    */
    public void kursZuTeilnehmer(ActionEvent actionEvent) {

        if (KvModel.aktuellePerson == null || tableViewKurse.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Boolean test_is_kurs = PersonKursListe.modelKP.addPersonInKursAlsTeilnehmer(KvModel.aktuellePerson,
                (Kurs) tableViewKurse.getSelectionModel().getSelectedItem());

        if (test_is_kurs) {
            tableViewTeilnehmerZu.getItems().add(tableViewKurse.getSelectionModel().getSelectedItem());
        }

    }


    public void kursZuInteressent(ActionEvent actionEvent) {
    }
}
