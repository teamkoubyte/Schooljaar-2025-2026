module com.example.inleidingtotovererving {
    // Dit zijn de modules die al in je Azul Zulu zitten:
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.inleidingtotovererving to javafx.fxml;
    exports com.example.inleidingtotovererving;
}