package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.Kurs;
import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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

    public ObservableList<String> choiceListAnrede = FXCollections.observableArrayList();

    public Tab fxmlPersonenDetails;

    static public boolean zurueckPersonenliste = false;
    static public boolean bearbeiten = false;
    @FXML
    public TableView tableViewKurse;
    @FXML
    public Label kursliste;
    @FXML
    public TableColumn kursname;
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
        status.setCellValueFactory(new PropertyValueFactory<Kurs, String>("status"));
        status.setCellFactory(TextFieldTableCell.<Kurs>forTableColumn());

        tableViewKurse.setItems(KvModel.model.kursList);
        TableView.TableViewSelectionModel<Kurs> selectionModel =
                tableViewKurse.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);


    }

    @FXML
    public void onsaveclick() {

        if (KvModel.aktuellePerson != null) {
            KvModel.aktuellePerson.updatePerson(anrede.getValue().toString(), titel.getText(),
                    vorname.getText(), nachname.getText(), strasse.getText(), plz.getText(),
                    ort.getText(), email.getText(), telefon.getText());
            felderLeeren();
        } else {
            int aktuelleAnzPersonen = KvModel.personList.size();
            Person person = Person.addNewPerson(anrede.getValue().toString(), titel.getText(),
                    vorname.getText(), nachname.getText(), strasse.getText(), plz.getText(),
                    ort.getText(), email.getText(), telefon.getText());
            if (KvModel.personList.size() > aktuelleAnzPersonen) {
                felderLeeren();
            }
        }
        KvModel.aktuellePerson = null;
        Tab plTab = main.fxmlPersonenListeController.fxmlPersonenListe;
        plTab.getTabPane().getSelectionModel().select(plTab);
        main.fxmlPersonenListeController.table.refresh();
    }


    @FXML
    public void update(Person person) {
        String anrede = person.getAnrede();
        String titel = person.getTitel();
        String vorname = person.getVorname();
        String nachname = person.getNachname();
        String strasse = person.getStrasse();
        String plz = person.getPlz();
        String ort = person.getOrt();
        String email = person.getEmail();
        String telefon = person.getTelefon();
        for (int i = 0; i < choiceListAnrede.size(); i++) {
            if (choiceListAnrede.get(i).equals(anrede)) {
                this.anrede.getSelectionModel().select(i);
            }
        }

        this.titel.setText(titel);
        this.vorname.setText(vorname);
        this.nachname.setText(nachname);
        this.strasse.setText(strasse);
        this.plz.setText(plz);
        this.ort.setText(ort);
        this.email.setText(email);
        this.telefon.setText(telefon);
    }

    @FXML
    public void onabbrechenclick(ActionEvent event) {
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
    }

    public void teilnehmerZuInteressent(ActionEvent actionEvent) {
    }

    public void interessentZuTeilnehmer(ActionEvent actionEvent) {
    }

    public void AusInteressentRaus(ActionEvent actionEvent) {
    }

    public void ausTeilnehmerRaus(ActionEvent actionEvent) {


    }

    public void kursZuTeilnehmer(ActionEvent actionEvent) {


    }

    public void kursZuInteressent(ActionEvent actionEvent) {
    }
}
