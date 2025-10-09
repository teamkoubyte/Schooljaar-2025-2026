package com.example.toetsjavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        HBox root;
        VBox vBox1, vBox2;
        Label labelJe, labelTu, labelIlElle, labelNous, labelVous, labelIlsElles;
        Label conjJe, conjTu, conjIlElle, conjNous, conjVous, conjIlsElles;
        Button buttonIndicatif, buttonImparfait, buttonFutur, buttonSluiten;

        root = new HBox();
        root.setSpacing(25);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(0), new Insets(0))));
        root.setAlignment(Pos.CENTER);

        vBox1 = new VBox();
        vBox1.setSpacing(25);
        vBox1.setPrefWidth(400);
        vBox1.setPrefHeight(600);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPadding(new Insets(20));
        vBox1.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(35), new Insets(0))));

        vBox2 = new VBox();
        vBox2.setSpacing(25);
        vBox2.setPrefWidth(400);
        vBox2.setPrefHeight(600);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setPadding(new Insets(20));
        vBox2.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(5), new Insets(0))));

        labelJe = new Label("j'");
        labelTu = new Label("tu");
        labelIlElle = new Label("il/elle");
        labelNous = new Label("nous");
        labelVous = new Label("vous");
        labelIlsElles = new Label("ils/elles");

        conjJe = new Label("");
        conjTu = new Label("");
        conjIlElle = new Label("");
        conjNous = new Label("");
        conjVous = new Label("");
        conjIlsElles = new Label("");

        buttonIndicatif = new Button("Indicatif présent");
        buttonImparfait = new Button("Imparfait");
        buttonFutur = new Button("Futur simple");
        buttonSluiten = new Button("Sluiten");

        buttonSluiten.setBackground(new Background(new BackgroundFill(Color.FIREBRICK, new CornerRadii(0), new Insets(0))));
        buttonSluiten.setTextFill(Color.WHITESMOKE);

        buttonIndicatif.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                conjJe.setText("ai");
                conjTu.setText("as");
                conjIlElle.setText("a");
                conjNous.setText("avons");
                conjVous.setText("avez");
                conjIlsElles.setText("ont");

                buttonIndicatif.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(50), new Insets(0))));
                buttonImparfait.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(50), new Insets(0))));
                buttonFutur.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(50), new Insets(0))));
            }
        });

        buttonImparfait.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                conjJe.setText("avais");
                conjTu.setText("avais");
                conjIlElle.setText("avait");
                conjNous.setText("avions");
                conjVous.setText("aviez");
                conjIlsElles.setText("avaient");

                buttonIndicatif.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(50), new Insets(0))));
                buttonImparfait.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(50), new Insets(0))));
                buttonFutur.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(50), new Insets(0))));
            }
        });

        buttonFutur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                conjJe.setText("aurai");
                conjTu.setText("auras");
                conjIlElle.setText("aura");
                conjNous.setText("aurons");
                conjVous.setText("aurez");
                conjIlsElles.setText("auront");

                buttonIndicatif.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(50), new Insets(0))));
                buttonImparfait.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(50), new Insets(0))));
                buttonFutur.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(50), new Insets(0))));
            }
        });

        buttonSluiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        vBox1.getChildren().addAll(labelJe, labelTu, labelIlElle, labelNous, labelVous, labelIlsElles,
                buttonIndicatif, buttonImparfait);

        vBox2.getChildren().addAll(conjJe, conjTu, conjIlElle, conjNous, conjVous, conjIlsElles,
                buttonFutur, buttonSluiten);

        root.getChildren().addAll(vBox1, vBox2);

        scene = new Scene(root, 900, 700);
        scene.setFill(Color.DARKORANGE);
        primaryStage.setTitle("La conjugaison du verbe avoir");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}