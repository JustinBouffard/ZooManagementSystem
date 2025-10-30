package org.example.zoomanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.zoomanagementsystem.ImportHelper.createAnimals;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("ZooSystemManagement");
        stage.setScene(scene);
        stage.show();

        EnclosureCollection bigCats = createAnimals();
        System.out.println(bigCats.display());
    }

    public static void main(String[] args) {
        launch();
    }
}