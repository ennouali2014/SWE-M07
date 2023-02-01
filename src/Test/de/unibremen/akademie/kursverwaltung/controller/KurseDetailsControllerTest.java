package de.unibremen.akademie.kursverwaltung.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KurseDetailsControllerTest {

    KurseDetailsController kurseDetailsController = new KurseDetailsController();

    @Test
    void initialize() {
    }

    @Test
    void onClickAbbrechenKurs() {
    }

    @Test
    void teilnehmerlist() {
    }

    @Test
    void anzeigeZumAendernKurs() {
    }

    @Test
    void interessentenlist() {
    }

    @Test
    void onDatePickerAction() {
    }

    @Test
    void show() {
    }

    @Test
    void init() {
        MainController mainController = new MainController();
        kurseDetailsController.init(mainController);

        assertEquals(mainController, kurseDetailsController.getMain());
    }

    @Test
    void onClickSaveKurs() {
    }

    @Test
    void checkIsInt() {
    }

    @Test
    void checkIsDouble() {
    }

    @Test
    void checkIsDate() {
    }

    @Test
    void onClickPrintAnwesenheitsliste() {
    }

    @Test
    void hatKursTeilnehmer() {
    }
}