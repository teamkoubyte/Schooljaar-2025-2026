module com.example.abstracteklassenenabstractemethodes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.abstracteklassenenabstractemethodes to javafx.fxml;
    exports com.example.abstracteklassenenabstractemethodes;
}