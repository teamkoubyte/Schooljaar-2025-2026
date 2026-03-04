package com.example.abstracteklassenenabstractemethodes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        Vierkant vierkant = new Vierkant(Color.RED, 5);
        Rechthoek rechthoek = new Rechthoek(Color.GREEN, 8, 4);
        GelijkzijdigeDriehoek driehoek = new GelijkzijdigeDriehoek(Color.YELLOW, 6, 5);

        BigDecimal vierkantOmtrek = BigDecimal.valueOf(vierkant.berekenOmtrek());
        BigDecimal vierkantOppervlakte = BigDecimal.valueOf(vierkant.berekenOppervlakte());
        BigDecimal rechthoekOmtrek = BigDecimal.valueOf(rechthoek.berekenOmtrek());
        BigDecimal rechthoekOppervlakte = BigDecimal.valueOf(rechthoek.berekenOppervlakte());
        BigDecimal driehoekOmtrek = BigDecimal.valueOf(driehoek.berekenOmtrek());
        BigDecimal driehoekOppervlakte = BigDecimal.valueOf(driehoek.berekenOppervlakte());

        labelLijst.add(OpmaakVisueleElementen.maakLabel("Vierkant: " + vierkant));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Omtrek vierkant: " + vierkantOmtrek));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Oppervlakte vierkant: " + vierkantOppervlakte));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Rechthoek: " + rechthoek));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Omtrek rechthoek: " + rechthoekOmtrek));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Oppervlakte rechthoek: " + rechthoekOppervlakte));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("GelijkzijdigeDriehoek: " + driehoek));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Omtrek driehoek: " + driehoekOmtrek));
        labelLijst.add(OpmaakVisueleElementen.maakLabel("Oppervlakte driehoek: " + driehoekOppervlakte));

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
