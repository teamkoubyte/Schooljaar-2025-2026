module com.example.voorbeeldexamen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.voorbeeldexamen to javafx.fxml;
    exports com.example.voorbeeldexamen;
}