package com.example.combinatiecombinatie2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        HBox root;
        VBox vbox1, vbox2;
        Circle cirkel;
        Label v1labelKlas,v1labelTitularis;
        Label v2labelKlas, v2labelTitularis;

        //*****************Hbox (De hoofdcontainer)*******************
        root = new HBox(20);
        root.setAlignment(Pos.CENTER);

        root.setBackground(new Background(new BackgroundFill
                (Color.ORANGE, new CornerRadii(0), new Insets(0))));

        //*****************Vbox1*****************
        vbox1 = new VBox(10);
        vbox1.setMinWidth(400);
        vbox1.setPadding(new Insets(20));
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setBackground(new Background(new BackgroundFill
                (Color.BLUE, new CornerRadii(35), new Insets(20))));

        //*****************Vbox2*****************
        vbox2 = new VBox(40);
        vbox2.setMinWidth(250);
        vbox2.setPadding(new Insets(20));
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setBackground(new Background(new BackgroundFill
                (Color.PINK, new CornerRadii(5), new Insets(20))));

        //*****************Labels*****************
        v1labelKlas = new Label("6IF&CW");
        v1labelKlas.setTextFill(Color.WHITE);
        v1labelKlas.setFont(new Font(25));
        v1labelTitularis = new Label("Gerrit Wijns");
        v1labelTitularis.setTextFill(Color.YELLOWGREEN);
        v1labelTitularis.setFont(new Font(15));
        v2labelKlas = new Label("6A&D");
        v2labelTitularis = new Label("Steven Vermeulen");


        //*****************Cirkel*****************
        cirkel = new Circle(50);
        cirkel.setFill(Color.FIREBRICK);

        //**************De kinderen van de HBOX (root)********************
        root.getChildren().addAll(vbox1, vbox2, cirkel);
        //**************De kinderen van de VBOXEN********************
        vbox1.getChildren().addAll(v1labelKlas, v1labelTitularis);
        vbox2.getChildren().addAll(v2labelKlas, v2labelTitularis);
        //*****************Scene*****************
        scene = new Scene(root, 320, 240);
        //*****************Primary Stage**********************
        primaryStage.setTitle("Klassen en hun klastitularis");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
