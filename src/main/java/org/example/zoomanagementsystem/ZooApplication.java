package org.example.zoomanagementsystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.zoomanagementsystem.Controllers.EnclosureCollectionController;
import org.example.zoomanagementsystem.Helpers.ImportHelper;
import org.example.zoomanagementsystem.Model.CompositeEnclosureCollection;
import org.example.zoomanagementsystem.Model.EnclosureCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZooApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("enclosure-collection-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);

            EnclosureCollectionController controller = fxmlLoader.getController();

            List<EnclosureCollection> compositeEnclosureCollections = new ArrayList<>();
            CompositeEnclosureCollection bigCats = ImportHelper.createAnimals();
            compositeEnclosureCollections.add(bigCats);

            controller.setEnclosureCollectionView(compositeEnclosureCollections);

            stage.setTitle("Zoo Areas");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FXML: " + e.getMessage());
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}