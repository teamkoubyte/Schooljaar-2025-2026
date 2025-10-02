package com.example.bedrageninjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        GridPane root;

        root = new GridPane();
        root.setHgap(5);
        root.setVgap(10);
        root.setPadding(new Insets(20));

        scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Werken met bedragen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
