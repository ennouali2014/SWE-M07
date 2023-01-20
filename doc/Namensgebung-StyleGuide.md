Namenkonvention
===============
### FXML-Elemente

    Label lblHinweis;
    // Präfix: lbl für Label + Bezeichnung

    ChoiceBox choiceAnrede;
    // Präfix: choice für ChoiceBox + Bezeichnung
    
    DatePicker pickEndeDatum;
    // Präfix: pick für DatePicker + Bezeichnung
    
    TextField txInpVorname;
    TextField txInpTelefon;
    // Präfix: txInp für TextField + Bezeichnung
    
    Button btnSavePersonDetails;
    Button btnCancelPersonDetails;
    // Präfix: btn für Button + Bezeichnung (Bezug zur Klasse, falls Standard-Action damit verbunden ist)
    
    Button btnInteressentZuTeilnehmer;
    Button btnTeilnehmerZuInteressent;
    Button btnInteressentKursRaus;
    Button btnInteressentKursRein;
    Button btnTeilnehmerKursRaus;
    Button btnTeilnehmerKursRein;
    // Präfix: btn für Button + Bezeichnung (Bezug zur onClick-Action/Methode)
    
    Tab tabPersonenDetails;
    // Präfix: tab für Tab + Bezeichnung (in diesem Fall der Bezug zum entsprechenden FXML/Controller)
    
    TableView tableKurse;
    // Präfix: table für TableView + Bezeichnung
    
    TableColumn colKurseKursname;
    TableColumn colKurseStartDate;
    // Präfix: col für TableColumn + Namer der Tabelle, gefolgt vom Spaltennamen
    
    TableView tableTeilnahmeKurse;
    TableColumn colTeilnahmeKurseKursname;
    
    TableView tableInteresseKurse;
    TableColumn colInteresseKurseKursname;

### FXML-Actions

    // Beispiele:
    public void onClickResetSuchfeld(ActionEvent event) {
        txInpPersonSuche.clear();
        table.getItems();
    }
    void onClickAbbrechenKurs(ActionEvent actionEvent)
    void onClickSavePerson()
    void onClickAnzeigeAendernPerson(Person person)
    void onClickTeilnehmerZuInteressent(ActionEvent actionEvent)
    void onClickKursRausAusTeilnehmer(ActionEvent actionEvent)
    // Präfix: onClick + Bezeichnung (Beschreibende Benennung der Aktion)