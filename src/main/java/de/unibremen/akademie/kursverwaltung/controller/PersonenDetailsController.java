package de.unibremen.akademie.kursverwaltung.controller;

import de.unibremen.akademie.kursverwaltung.domain.KvModel;
import de.unibremen.akademie.kursverwaltung.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class PersonenDetailsController {
    @FXML
    public ChoiceBox anrede;
    @FXML

    public static boolean zurueckwechseln;
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
    static public boolean updateExistingPerson;
    static public boolean zurueckPersonenliste = false;

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
    }

    @FXML
    public void onsaveclick() {
        int aktuelleAnzPersonen = KvModel.personList.size();
        if (updateExistingPerson) {
            KvModel.aktuellePerson.updatePerson(anrede.getValue().toString(), titel.getText(), vorname.getText(),
                    nachname.getText(), strasse.getText(), plz.getText(), ort.getText(), email.getText(), telefon.getText());
            felderLeeren();

        } else {

            Person person = Person.addNewPerson(anrede.getValue().toString(), titel.getText(), vorname.getText(),
                    nachname.getText(), strasse.getText(), plz.getText(), ort.getText(), email.getText(), telefon.getText());
            if (KvModel.personList.size() > aktuelleAnzPersonen) {
                felderLeeren();
            }
        }
        updateExistingPerson = false;

        System.out.println(zurueckPersonenliste);
        for (Tab tabPaneKursAnlegen : fxmlPersonenDetails.getTabPane().getTabs()) {
            if (tabPaneKursAnlegen.getText().equals("Personen-Liste")) {
                tabPaneKursAnlegen.getTabPane().getSelectionModel().select(tabPaneKursAnlegen);
            }
        }

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
            for (Tab tabPanePersonAnlegen : fxmlPersonenDetails.getTabPane().getTabs()) {
                if (tabPanePersonAnlegen.getText().equals("Personen-Liste")) {
                    tabPanePersonAnlegen.getTabPane().getSelectionModel().select(tabPanePersonAnlegen);
                }
            }
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
}
