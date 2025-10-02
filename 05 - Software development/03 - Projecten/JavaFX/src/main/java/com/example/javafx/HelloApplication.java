package com.example.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        VBox root = new VBox(5);
        MenuBar menubalk;
        
        // Menu's
        Menu menuCirkels, menuRechthoek;
        
        // Menu items voor Cirkels
        MenuItem linkerCirkelRoodGeel, linkerCirkelGeelRood;
        MenuItem rechterCirkelBruinZwart, rechterCirkelZwartBruin;
        
        // Menu items voor Rechthoek
        MenuItem groenBlauw, blauwGroen;
        
        FlowPane flowPane;
        
        // Vormen met exacte afmetingen volgens opdracht
        Circle cirkelStraal30, cirkelStraal40, cirkelStraal50, cirkelStraal60;
        Rectangle rechthoek;
        Button sluitenButton;
        
        // ***** Menu balk maken *****
        menubalk = new MenuBar();
        menuCirkels = new Menu("Cirkels");
        menuRechthoek = new Menu("Rechthoek");
        
        // ***** Menu items voor Cirkels *****
        linkerCirkelRoodGeel = new MenuItem("Linker cirkels rood met gele rand");
        linkerCirkelGeelRood = new MenuItem("Linker cirkels geel met rode rand");
        rechterCirkelBruinZwart = new MenuItem("Rechter cirkels bruin met zwarte rand");
        rechterCirkelZwartBruin = new MenuItem("Rechter cirkels zwart met bruine rand");
        
        menuCirkels.getItems().addAll(linkerCirkelRoodGeel, linkerCirkelGeelRood, 
                                     rechterCirkelBruinZwart, rechterCirkelZwartBruin);
        
        // ***** Menu items voor Rechthoek *****
        groenBlauw = new MenuItem("Groen met blauwe rand");
        blauwGroen = new MenuItem("Blauw met groene rand");
        
        menuRechthoek.getItems().addAll(groenBlauw, blauwGroen);
        
        // ***** Menu's toevoegen aan menubalk *****
        menubalk.getMenus().addAll(menuCirkels, menuRechthoek);
        
        // ***** FlowPane voor vormen *****
        flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(30);
        flowPane.setAlignment(Pos.CENTER);
        
        // ***** Vormen maken volgens opdracht *****
        // Linker cirkels (straal 30 en 50)
        cirkelStraal30 = new Circle(30);
        cirkelStraal30.setFill(Color.BLACK);
        cirkelStraal30.setStroke(Color.BLACK);
        
        cirkelStraal50 = new Circle(50);
        cirkelStraal50.setFill(Color.BLACK);
        cirkelStraal50.setStroke(Color.BLACK);
        
        // Rechthoek (100x100)
        rechthoek = new Rectangle(100, 100);
        rechthoek.setFill(Color.BLACK);
        rechthoek.setStroke(Color.BLACK);
        
        // Rechter cirkels (straal 40 en 60)
        cirkelStraal40 = new Circle(40);
        cirkelStraal40.setFill(Color.BLACK);
        cirkelStraal40.setStroke(Color.BLACK);
        
        cirkelStraal60 = new Circle(60);
        cirkelStraal60.setFill(Color.BLACK);
        cirkelStraal60.setStroke(Color.BLACK);
        
        // ***** Event handlers voor menu items *****
        linkerCirkelRoodGeel.setOnAction(e -> {
            cirkelStraal30.setFill(Color.RED);
            cirkelStraal30.setStroke(Color.YELLOW);
            cirkelStraal50.setFill(Color.RED);
            cirkelStraal50.setStroke(Color.YELLOW);
        });
        
        linkerCirkelGeelRood.setOnAction(e -> {
            cirkelStraal30.setFill(Color.YELLOW);
            cirkelStraal30.setStroke(Color.RED);
            cirkelStraal50.setFill(Color.YELLOW);
            cirkelStraal50.setStroke(Color.RED);
        });
        
        rechterCirkelBruinZwart.setOnAction(e -> {
            cirkelStraal40.setFill(Color.BROWN);
            cirkelStraal40.setStroke(Color.BLACK);
            cirkelStraal60.setFill(Color.BROWN);
            cirkelStraal60.setStroke(Color.BLACK);
        });
        
        rechterCirkelZwartBruin.setOnAction(e -> {
            cirkelStraal40.setFill(Color.BLACK);
            cirkelStraal40.setStroke(Color.BROWN);
            cirkelStraal60.setFill(Color.BLACK);
            cirkelStraal60.setStroke(Color.BROWN);
        });
        
        groenBlauw.setOnAction(e -> {
            rechthoek.setFill(Color.GREEN);
            rechthoek.setStroke(Color.BLUE);
        });
        
        blauwGroen.setOnAction(e -> {
            rechthoek.setFill(Color.BLUE);
            rechthoek.setStroke(Color.GREEN);
        });
        
        // ***** Vormen toevoegen aan FlowPane *****
        flowPane.getChildren().addAll(cirkelStraal30, cirkelStraal40, rechthoek, 
                                     cirkelStraal50, cirkelStraal60);
        
        // ***** Sluiten button *****
        sluitenButton = new Button("Sluiten");
        sluitenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        
        // ***** Alles bij elkaar voegen *****
        root.getChildren().addAll(menubalk, flowPane, sluitenButton);
        
        // ***** Scene maken met exacte afmetingen *****
        scene = new Scene(root, 335, 430);
        scene.setFill(Color.LIGHTCYAN);
        
        primaryStage.setTitle("Vier cirkels en een rechthoek");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}