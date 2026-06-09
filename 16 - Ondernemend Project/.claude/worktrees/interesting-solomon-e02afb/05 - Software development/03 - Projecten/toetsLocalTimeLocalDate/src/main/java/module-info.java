module com.example.toetslocaltimelocaldate {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.toetslocaltimelocaldate to javafx.fxml;
    exports com.example.toetslocaltimelocaldate;
}