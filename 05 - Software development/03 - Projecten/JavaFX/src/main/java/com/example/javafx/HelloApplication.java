package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HelloApplication extends Application {
    private Circle linkerCirkel;
    private Circle rechterCirkel;
    private Circle onderLinkerCirkel;
    private Circle onderRechterCirkel;
    private Rectangle rechthoek;

    public void start(Stage primaryStage) {
        // Maak het hoofdvenster (BorderPane)
        BorderPane root = new BorderPane();

        // Maak de menubalk
        MenuBar menuBar = new MenuBar();

        // Maak het Cirkels menu
        Menu cirkelsMenu = new Menu("Cirkels");

        // Menu-items voor Cirkels
        MenuItem linkerRoodGeel = new MenuItem("Linker cirkels rood met gele rand");
        MenuItem linkerGeelRood = new MenuItem("Linker cirkels geel met rode rand");
        MenuItem rechterBruinZwart = new MenuItem("Rechter cirkels bruin met zwarte rand");
        MenuItem rechterZwartBruin = new MenuItem("Rechter cirkels zwart met bruine rand");

        cirkelsMenu.getItems().addAll(linkerRoodGeel, linkerGeelRood,
                rechterBruinZwart, rechterZwartBruin);

        // Maak het Rechthoek menu
        Menu rechthoekMenu = new Menu("Rechthoek");

        // Menu-items voor Rechthoek
        MenuItem groenBlauw = new MenuItem("Groen met blauwe rand");
        MenuItem blauwGroen = new MenuItem("Blauw met groene rand");

        rechthoekMenu.getItems().addAll(groenBlauw, blauwGroen);

        // Voeg menu's toe aan menubalk
        menuBar.getMenus().addAll(cirkelsMenu, rechthoekMenu);

        // Maak een Pane voor de shapes
        Pane canvas = new Pane();
        canvas.setPrefSize(600, 400);
        canvas.setStyle("-fx-background-color: lightgray;");

        // Maak de cirkels volgens de specificaties
        // Linksboven: straal 30
        linkerCirkel = new Circle(150, 100, 30);
        linkerCirkel.setFill(Color.BLACK);

        // Rechtsboven: straal 40
        rechterCirkel = new Circle(450, 100, 40);
        rechterCirkel.setFill(Color.BLACK);

        // Linksonder: straal 50
        onderLinkerCirkel = new Circle(150, 300, 50);
        onderLinkerCirkel.setFill(Color.BLACK);

        // Rechtsonder: straal 60 (achtergrondkleur lichtpuntrood)
        onderRechterCirkel = new Circle(450, 300, 60);
        onderRechterCirkel.setFill(Color.BLACK);

        // Maak de rechthoek (100x100) in het midden
        rechthoek = new Rectangle(275, 175, 100, 100);
        rechthoek.setFill(Color.BLACK);

        // Voeg alle shapes toe aan de canvas
        canvas.getChildren().addAll(linkerCirkel, rechterCirkel,
                rechthoek,
                onderLinkerCirkel, onderRechterCirkel);

        // Event handlers voor Cirkels menu
        linkerRoodGeel.setOnAction(e -> {
            // Linker cirkels: rood met gele rand
            linkerCirkel.setFill(Color.RED);
            linkerCirkel.setStroke(Color.YELLOW);
            linkerCirkel.setStrokeWidth(3);

            onderLinkerCirkel.setFill(Color.RED);
            onderLinkerCirkel.setStroke(Color.YELLOW);
            onderLinkerCirkel.setStrokeWidth(3);
        });

        linkerGeelRood.setOnAction(e -> {
            // Linker cirkels: geel met rode rand
            linkerCirkel.setFill(Color.YELLOW);
            linkerCirkel.setStroke(Color.RED);
            linkerCirkel.setStrokeWidth(3);

            onderLinkerCirkel.setFill(Color.YELLOW);
            onderLinkerCirkel.setStroke(Color.RED);
            onderLinkerCirkel.setStrokeWidth(3);
        });

        rechterBruinZwart.setOnAction(e -> {
            // Rechter cirkels: bruin met zwarte rand
            rechterCirkel.setFill(Color.BROWN);
            rechterCirkel.setStroke(Color.BLACK);
            rechterCirkel.setStrokeWidth(3);

            onderRechterCirkel.setFill(Color.BROWN);
            onderRechterCirkel.setStroke(Color.BLACK);
            onderRechterCirkel.setStrokeWidth(3);
        });

        rechterZwartBruin.setOnAction(e -> {
            // Rechter cirkels: zwart met bruine rand
            rechterCirkel.setFill(Color.BLACK);
            rechterCirkel.setStroke(Color.BROWN);
            rechterCirkel.setStrokeWidth(3);

            onderRechterCirkel.setFill(Color.BLACK);
            onderRechterCirkel.setStroke(Color.BROWN);
            onderRechterCirkel.setStrokeWidth(3);
        });

        // Event handlers voor Rechthoek menu
        groenBlauw.setOnAction(e -> {
            // Rechthoek: groen met blauwe rand
            rechthoek.setFill(Color.GREEN);
            rechthoek.setStroke(Color.BLUE);
            rechthoek.setStrokeWidth(3);
        });

        blauwGroen.setOnAction(e -> {
            // Rechthoek: blauw met groene rand
            rechthoek.setFill(Color.BLUE);
            rechthoek.setStroke(Color.GREEN);
            rechthoek.setStrokeWidth(3);
        });

        // Stel de layout in
        root.setTop(menuBar);
        root.setCenter(canvas);

        // Maak en configureer de scene
        Scene scene = new Scene(root, 600, 450);

        // Configureer het venster
        primaryStage.setTitle("JavaFX Toets - Cirkels en Rechthoek");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}