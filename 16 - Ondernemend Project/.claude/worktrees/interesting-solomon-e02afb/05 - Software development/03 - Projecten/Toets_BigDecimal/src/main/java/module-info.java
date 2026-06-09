module com.example.toets_bigdecimal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.toets_bigdecimal to javafx.fxml;
    exports com.example.toets_bigdecimal;
}