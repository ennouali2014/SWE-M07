
package de.unibremen.akademie.kursverwaltung.controller;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.MenuButton;
        import javafx.scene.control.TextField;

public class KurseListeController {

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

    @FXML
    void s(ActionEvent event) {

    }
    public void initialize() {
        // TODO: Make the list editable to make the whole program simpler.
        // TODO: Select item statt select items usw. benutzen?
        System.out.println("Init");
        itemList.itemsProperty().bind(EinkaufslistenModel.model.einkaufsliste);
        actionButton.setText("Action");
        actionButton.setDisable(true);
//        ChangeListener<String> addFieldListener = new ChangeListener<String>() {
//
//            @Override
//            public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                System.out.println("TextField Text Changed (oldValue: " + oldValue + " -> newValue: " + newValue + ")");
//                if (newValue.length() == 0) {
//                    actionButton.setDisable(true);
//                    actionButton.setText("Action");
//                } else {
//                    actionButton.setDisable(false);
//                    actionButton.setText("Add Item");
//                }
//
//            }
//        };
//        addField.textProperty().addListener(addFieldListener);
        addField.textProperty().addListener( (observable,oldValue,newValue) -> {
            if (newValue.length() == 0) {
                actionButton.setDisable(true);
                actionButton.setText("Action");
            } else {
                actionButton.setDisable(false);
                actionButton.setText("Add Item");
            }
        } );


    }
