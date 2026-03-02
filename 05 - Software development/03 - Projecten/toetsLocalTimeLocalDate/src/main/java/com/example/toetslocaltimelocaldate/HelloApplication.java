package com.example.toetslocaltimelocaldate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import static com.example.toetslocaltimelocaldate.BewerkingenOpDatums.*;
import static com.example.toetslocaltimelocaldate.BewerkingenOpTijdstippen.*;
import static com.example.toetslocaltimelocaldate.OpmaakVisueleElementen.maakVBox;
import static com.example.toetslocaltimelocaldate.OpmaakVisueleElementen.maakLabel;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        BorderPane root;
        VBox vbox;
        ScrollPane scrollPane;
        ArrayList<Label> labelLijst = new ArrayList<Label>();
        int jaarVanKerstdag;
        LocalDate begindatumKerstvakantie, einddatumKerstvakantie;
        LocalTime[][] aankomsttijdenMatrix;
        ArrayList<Integer> vertragingenPerHalte;
        int somVanVertragingen, totaleRitduur;

        //*****testcode*****
        jaarVanKerstdag = 2026;
        begindatumKerstvakantie = berekenBegindatumKerstvakantie(jaarVanKerstdag);
        einddatumKerstvakantie = berekenEinddatumKerstvakantie(jaarVanKerstdag);

        labelLijst.add(maakLabel("Werken met datums"));
        labelLijst.add(maakLabel("Kerstdag in jaar " + jaarVanKerstdag + ": "
                + converteerDatumInString(LocalDate.of(jaarVanKerstdag, 12, 25), FormatStyle.FULL)));
        labelLijst.add(maakLabel("Begindatum kerstvakantie: "
                + converteerDatumInString(begindatumKerstvakantie, FormatStyle.FULL)));
        labelLijst.add(maakLabel("Einddatum kerstvakantie: "
                + converteerDatumInString(einddatumKerstvakantie, FormatStyle.FULL)));

        aankomsttijdenMatrix = new LocalTime[][]{
                {LocalTime.of(22, 26), LocalTime.of(22, 27)},
                {LocalTime.of(22, 32), LocalTime.of(22, 32)},
                {LocalTime.of(22, 43), LocalTime.of(22, 44)},
                {LocalTime.of(22, 48), LocalTime.of(22, 48)},
                {LocalTime.of(22, 53), LocalTime.of(22, 53)}
        };
        vertragingenPerHalte = berekenVertragingenPerHalte(aankomsttijdenMatrix);
        somVanVertragingen = berekenSomVanVertragingen(aankomsttijdenMatrix);
        totaleRitduur = berekenTotaleRitduur(aankomsttijdenMatrix);

        labelLijst.add(maakLabel(""));
        labelLijst.add(maakLabel("Werken met tijdstippen"));
        labelLijst.add(maakLabel("Vertraging per halte (in minuten): " + vertragingenPerHalte));
        labelLijst.add(maakLabel("Som van alle vertragingen (in minuten): " + somVanVertragingen));
        labelLijst.add(maakLabel("Totale ritduur (in minuten): " + totaleRitduur));


        // *****vbox*****
        vbox = maakVBox();
        vbox.getChildren().addAll(labelLijst);

        //*****ScrollPane*****
        scrollPane = new ScrollPane(vbox);

        //*****root (border pane)*****
        root = new BorderPane(scrollPane);

        //*****scene*****
        scene = new Scene(root, vbox.getMinWidth(), 700);

        //*****primary stage*****
        primaryStage.setTitle("Toets: Werken met datums en tijdstippen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
