module com.example.examen1zandpoortbus {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examen1zandpoortbus to javafx.fxml;
    exports com.example.examen1zandpoortbus;
}