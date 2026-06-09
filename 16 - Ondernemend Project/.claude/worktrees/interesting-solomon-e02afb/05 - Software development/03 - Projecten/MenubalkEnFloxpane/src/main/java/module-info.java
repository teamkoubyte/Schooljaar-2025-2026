module com.example.menubalkenfloxpane {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.menubalkenfloxpane to javafx.fxml;
    exports com.example.menubalkenfloxpane;
}