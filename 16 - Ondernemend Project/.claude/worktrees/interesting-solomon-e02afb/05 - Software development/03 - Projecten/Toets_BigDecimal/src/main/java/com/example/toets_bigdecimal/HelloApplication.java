package com.example.toets_bigdecimal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Scene scene;
        BorderPane root;
        VBox vbox;
        ScrollPane scrollPane;
        ArrayList<Label> labelLijst = new ArrayList<Label>();

        //*****testcode*****
        ArrayList<BigDecimal> oorspronkelijkePrijzenLijst = new ArrayList<BigDecimal>();
        oorspronkelijkePrijzenLijst.add(BewerkingenOpBedragen.converteerInEur(55.60));
        oorspronkelijkePrijzenLijst.add(BewerkingenOpBedragen.converteerInEur(27.25));
        oorspronkelijkePrijzenLijst.add(BewerkingenOpBedragen.converteerInEur(14.20));

        ArrayList<BigDecimal> teBetalenBedragenLijst = BewerkingenOpBedragen.berekenTeBetalenBedragen(oorspronkelijkePrijzenLijst);
        ArrayList<BigDecimal> toegepasteKortingenLijst = BewerkingenOpBedragen.berekenToegepasteKortingen(oorspronkelijkePrijzenLijst);
        BigDecimal somVanKortingen = BewerkingenOpBedragen.berekenSomVanToegepasteKortingen(oorspronkelijkePrijzenLijst);

        labelLijst.add(OpmaakVisueleElementen.maakLabel("Oorspronkelijke prijzen: " + oorspronkelijkePrijzenLijst));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Te betalen bedragen: " + teBetalenBedragenLijst));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Toegepaste kortingen: " + toegepasteKortingenLijst));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Som van kortingen: " + somVanKortingen));

        // *****vbox*****
        vbox = OpmaakVisueleElementen.maakVBox();
        vbox.getChildren().addAll(labelLijst);

        //*****ScrollPane*****
        scrollPane = new ScrollPane(vbox);

        //*****root (border pane)*****
        root = new BorderPane(scrollPane);

        //*****scene*****
        scene = new Scene(root, vbox.getMinWidth(), 250);

        //*****primary stage*****
        primaryStage.setTitle("Testen BigDecimal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}