package com.example.toets_bigdecimal;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OpmaakVisueleElementen {
    public static Label maakLabel(String tekst) {
        Label label;
        label = new Label(tekst);
        label.setFont(new Font(16));
        label.setTextFill(Color.WHITE);
        return (label);
    }

    public static VBox maakVBox() {
        VBox vbox;
        vbox = new VBox(10);
        vbox.setMinWidth(600);
        vbox.setPadding(new Insets(20));
        vbox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE,new CornerRadii(10),new Insets(0))));
        return (vbox);
    }
}