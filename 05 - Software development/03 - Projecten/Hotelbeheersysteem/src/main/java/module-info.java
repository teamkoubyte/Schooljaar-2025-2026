module com.example.hotelbeheersysteem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelbeheersysteem to javafx.fxml;
    exports com.example.hotelbeheersysteem;
}