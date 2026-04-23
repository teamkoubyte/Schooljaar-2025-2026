package com.example.hotelbeheersysteem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.hotelbeheersysteem.OpmaakVisueleElementen.maakLabel;
import static com.example.hotelbeheersysteem.OpmaakVisueleElementen.maakVBox;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Hotel hotel = new Hotel("H001", "Hotel Zandpoort", "Een gezellig hotel aan de kust", "Zandpoortstraat 1", "info@hotelzandpoort.be", "050 12 34 56", 4);

        VBox vbox = maakVBox();
        vbox.getChildren().add(maakLabel(hotel.toString()));

        ScrollPane scrollPane = new ScrollPane(vbox);
        BorderPane root = new BorderPane(scrollPane);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Hotelbeheersysteem");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}