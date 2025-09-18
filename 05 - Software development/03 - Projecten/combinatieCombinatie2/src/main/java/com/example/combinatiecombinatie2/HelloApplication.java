package com.example.combinatiecombinatie2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        HBox root;

        //*****************Hbox (De hoofdcontainer)*******************
        root = new HBox(20);
        root.setAlignment(Pos.CENTER);

        root.setBackground(new Background(new BackgroundFill
                (Color.ORANGE, new CornerRadii(0), new Insets(0))));

        //*****************Scene*****************
        scene = new Scene(root, 320, 240);
        //*****************Primary Stage**********************
        primaryStage.setTitle("Klassen en hun klastitularis");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
