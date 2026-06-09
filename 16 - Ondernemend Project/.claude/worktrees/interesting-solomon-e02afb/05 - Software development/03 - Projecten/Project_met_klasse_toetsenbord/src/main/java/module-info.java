module com.example.project_met_klasse_toetsenbord {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_met_klasse_toetsenbord to javafx.fxml;
    exports com.example.project_met_klasse_toetsenbord;
}