module com.example.combinatiecombinatie2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.combinatiecombinatie2 to javafx.fxml;
    exports com.example.combinatiecombinatie2;
}