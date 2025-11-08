package org.example.zoomanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.zoomanagementsystem.Controllers.CompositeEnclosureCollectionController;
import org.example.zoomanagementsystem.Helpers.ImportHelper;
import org.example.zoomanagementsystem.Model.CompositeEnclosureCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZooApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("composite-enclosure-collection-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        CompositeEnclosureCollectionController controller = fxmlLoader.getController();

        List<CompositeEnclosureCollection> compositeEnclosureCollections = new ArrayList<>();
        CompositeEnclosureCollection bigCats = ImportHelper.createAnimals();
        compositeEnclosureCollections.add(bigCats);

        controller.setCompositeEnclosureCollectionView(compositeEnclosureCollections);

        stage.setTitle("Zoo Areas");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}