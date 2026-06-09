package com.example.menubalkenfloxpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        VBox root = new VBox(5);
        MenuBar menubalk;
        FlowPane flowPane;

        //*********************Scene*************************
        root.getChildren().addAll(menubalk, flowPane);
        scene = new Scene(root, 400, 350);
        //****************Primary Stage**********************
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
