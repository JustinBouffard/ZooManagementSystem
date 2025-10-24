module org.example.zoomanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.zoomanagementsystem to javafx.fxml;
    exports org.example.zoomanagementsystem;
}