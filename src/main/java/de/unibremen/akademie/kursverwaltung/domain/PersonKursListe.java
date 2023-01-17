package de.unibremen.akademie.kursverwaltung.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonKursListe {
    private ObservableList<PersonKurs> personKursList =
            FXCollections.observableArrayList();


    public ObservableList<PersonKurs> getPersonKursList() {
        return personKursList;
    }

    public void setPersonKursList(ObservableList<PersonKurs> personKursList) {
        this.personKursList = personKursList;
    }
}
