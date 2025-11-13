module org.example.zoomanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.zoomanagementsystem to javafx.fxml;
    exports org.example.zoomanagementsystem;
    exports org.example.zoomanagementsystem.Controllers;
    opens org.example.zoomanagementsystem.Controllers to javafx.fxml;
    exports org.example.zoomanagementsystem.Model;
    opens org.example.zoomanagementsystem.Model to javafx.fxml;
}