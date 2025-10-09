module com.example.toetsjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.toetsjavafx to javafx.fxml;
    exports com.example.toetsjavafx;
}