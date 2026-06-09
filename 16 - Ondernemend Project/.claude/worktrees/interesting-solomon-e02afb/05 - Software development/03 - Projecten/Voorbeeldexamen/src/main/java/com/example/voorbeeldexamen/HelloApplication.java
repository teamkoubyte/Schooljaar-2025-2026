package com.example.voorbeeldexamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.voorbeeldexamen6ad20251125.OpmaakVisueleElementen.maakLabel;
import static com.example.voorbeeldexamen6ad20251125.OpmaakVisueleElementen.maakVBox;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        BorderPane root;
        VBox vbox;
        ScrollPane scrollPane;
        ArrayList<Label> labelLijst = new ArrayList<Label>();
        Vrijwilliger vrijwilliger1, vrijwilliger2;
        Activiteit activiteit1, activiteit2;
        LokaleAfdeling lokaleAfdeling1, lokaleAfdeling2;
        Organisatie organisatie; //*****testcode***** vrijwilliger1 = new Vrijwilliger("Steven", "Vermeulen", "SV"); labelLijst.add(maakLabel("*****toString van vrijwilliger1*****")); labelLijst.add(maakLabel(vrijwilliger1.toString())); vrijwilliger1.setProfiel("vertaler"); vrijwilliger1.setEmail("steven.vermeulen@hotmail.com"); vrijwilliger1.setTelefoon("03 218 54 78"); labelLijst.add(maakLabel("*****toString van vrijwilliger1*****")); labelLijst.add(maakLabel(vrijwilliger1.toString())); labelLijst.add(maakLabel("Profiel: " + vrijwilliger1.getProfiel())); labelLijst.add(maakLabel("E-mail: " + vrijwilliger1.getEmail())); labelLijst.add(maakLabel("Telefoon: " + vrijwilliger1.getTelefoon())); activiteit1 = new Activiteit("Boekvoorstelling", "BO", "Het nieuwe boek van Amir Bachrouri wordt voorgesteld."); activiteit1.setLocatie("De Roma, Antwerpen"); labelLijst.add(maakLabel("*****toString van activiteit1*****")); labelLijst.add(maakLabel(activiteit1.toString())); lokaleAfdeling1 = new LokaleAfdeling("Antwerpen", "ANT"); lokaleAfdeling1.voegVrijwilligerToe(vrijwilliger1);

        lokaleAfdeling1.voegActviteitToe(activiteit1);
        labelLijst.add(maakLabel("***