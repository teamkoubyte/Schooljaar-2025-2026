package com.example.examen1zandpoortbus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.Month;

import static com.example.examen1zandpoortbus.OpmaakVisueleElementen.maakLabel;
import static com.example.examen1zandpoortbus.OpmaakVisueleElementen.maakVBox;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Busmaatschappij maatschappij = new Busmaatschappij("ZB", "Zandpoortbus", "www.zandpoortbus.com");

        Rit rit1 = new Rit("ZB1774", "Brussel", "Parijs");
        rit1.setAantalReizigers(61);
        rit1.setMaand(Month.JANUARY);
        rit1.setRittijdstip(Rittijdstip.NAMIDDAGRIT);
        rit1.voegChauffeurToe(new Chauffeur("4006", "Samira Diallo", SoortArbeidsovereenkomst.LOONTREKKENDE));
        rit1.voegChauffeurToe(new Chauffeur("5003", "Jan Jansens", SoortArbeidsovereenkomst.INTERIM));

        Rit rit2 = new Rit("ZB4773", "Brussel", "Amsterdam");
        rit2.setAantalReizigers(52);
        rit2.setMaand(Month.JANUARY);
        rit2.setRittijdstip(Rittijdstip.OCHTENDRIT);
        rit2.voegChauffeurToe(new Chauffeur("5004", "Steven Vandaele", SoortArbeidsovereenkomst.ZELFSTANDIGE));

        maatschappij.voegRitToe(rit1);
        maatschappij.voegRitToe(rit2);

        VBox vbox = maakVBox();
        vbox.getChildren().add(maakLabel(maatschappij.toString()));

        ScrollPane scrollPane = new ScrollPane(vbox);
        BorderPane root = new BorderPane(scrollPane);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Zandpoortbus overzicht");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}