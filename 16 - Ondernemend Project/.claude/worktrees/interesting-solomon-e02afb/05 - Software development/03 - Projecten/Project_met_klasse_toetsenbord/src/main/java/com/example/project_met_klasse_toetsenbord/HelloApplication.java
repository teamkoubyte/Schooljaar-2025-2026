package com.example.project_met_klasse_toetsenbord;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        VBox root;
        Toetsenbord toetsenbord;
        Label labelSerienummer, labelMerk, labelDraadloos, labelPrijs, labelPrijs5toetsenborden, labelToString;

        //**********Instantiëren labels
        labelSerienummer = new Label("Serienummer");
        labelMerk = new Label("Merk");
        labelDraadloos = new Label("Draadloos");
        labelPrijs = new Label("Prijs");
        labelPrijs5toetsenborden = new Label("Prijs van 5 toetsenborden");
        labelToString = new Label("toString");
        //**********Toetsenbord**********
        toetsenbord = new Toetsenbord("Apple"); toetsenbord = new Toetsenbord();
        toetsenbord.setMerk("Logitech"); toetsenbord.setSerienummer("xxx-yyy-zzz");
        toetsenbord.setDraadloos(false);
        toetsenbord.setPrijs(BigDecimal.valueOf(50).setScale(2, RoundingMode.HALF_UP));
        labelMerk.setText("Merk: " + toetsenbord.getMerk());
        labelSerienummer.setText("Serienummer: " + toetsenbord.getSerienummer());
        labelDraadloos.setText("Draadloos: " + toetsenbord.isDraadloos());
        labelPrijs.setText("Prijs: " + toetsenbord.getPrijs());
        labelPrijs5toetsenborden.setText("Prijs van 5 toetsenborden: " + toetsenbord.berekenPrijs(5));
        labelToString.setText("toString: " + toetsenbord.toString());
        //**********container**********
        root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(labelSerienummer, labelMerk, labelDraadloos, labelPrijs, labelPrijs5toetsenborden, labelToString);
        //*********Scene**********
        scene = new Scene(root, 200, 200);
        //**********Primary Stage**********
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

