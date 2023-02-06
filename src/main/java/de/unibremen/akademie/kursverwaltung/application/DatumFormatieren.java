package de.unibremen.akademie.kursverwaltung.application;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatumFormatieren {

    //  Datum für DatePicker formatieren - special thanx to chatGPT ;)
    public static void datumFormatieren(DatePicker datum) {
        DateTimeFormatter datumsFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        datum.setPromptText(datumsFormat.toString());
        datum.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date == null ? "" : datumsFormat.format(date);
            }

            @Override
            public LocalDate fromString(String string) {
                return string == null || string.isEmpty() ? null : LocalDate.parse(string, datumsFormat);
            }
        });

    }
}
