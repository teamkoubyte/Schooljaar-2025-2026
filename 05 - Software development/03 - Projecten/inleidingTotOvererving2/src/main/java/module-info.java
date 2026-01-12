module com.example.inleidingtotovererving2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inleidingtotovererving2 to javafx.fxml;
    exports com.example.inleidingtotovererving2;
}