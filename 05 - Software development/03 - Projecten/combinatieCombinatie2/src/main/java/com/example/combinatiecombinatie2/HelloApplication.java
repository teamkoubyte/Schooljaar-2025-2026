package com.example.combinatiecombinatie2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        HBox root;
        VBox vbox1, vbox2;
        Circle cirkel;

        //*****************Hbox (De hoofdcontainer)*******************
        root = new HBox(20);
        root.setAlignment(Pos.CENTER);

        root.setBackground(new Background(new BackgroundFill
                (Color.ORANGE, new CornerRadii(0), new Insets(0))));

        //*****************Vbox*****************
        vbox1 = new VBox(10);
        vbox1.setMinWidth(400);
        vbox1.setPadding(new Insets(20));
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setBackground(new Background(new BackgroundFill
                (Color.BLUE, new CornerRadii(35), new Insets(20))));

        //**************De kinderen van de HBOX********************
        root.getChildren().add(vbox1);
        //*****************Scene*****************
        scene = new Scene(root, 320, 240);
        //*****************Primary Stage**********************
        primaryStage.setTitle("Klassen en hun klastitularis");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
