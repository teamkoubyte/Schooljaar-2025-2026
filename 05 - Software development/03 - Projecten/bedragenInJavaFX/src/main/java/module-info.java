module com.example.bedrageninjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bedrageninjavafx to javafx.fxml;
    exports com.example.bedrageninjavafx;
}