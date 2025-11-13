package org.example.zoomanagementsystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.zoomanagementsystem.Model.Animal;
import org.example.zoomanagementsystem.Model.Enclosure;
import org.example.zoomanagementsystem.Model.EnclosureCollection;
import org.example.zoomanagementsystem.Model.Lion;
import org.example.zoomanagementsystem.Model.Tiger;
import org.example.zoomanagementsystem.Model.Cougar;


import java.io.IOException;
import java.util.Optional;

/**
 * @author Pascale Fontaine
 */
public class EnclosureController {

    @FXML private ListView<String> listAnimals;
    @FXML private Button btnAdd, btnDelete, btnBack, btnClose;

    private Enclosure currentEnclosure;

    @FXML
    public void initialize() {
        btnDelete.setDisable(true);
        btnAdd.setOnAction(e -> openAnimalView(null));
        btnDelete.setOnAction(e -> handleDelete());
        btnBack.setOnAction(e -> ((Stage) btnBack.getScene().getWindow()).close());
        btnClose.setOnAction(e -> System.exit(0));

        listAnimals.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selected) -> btnDelete.setDisable(selected == null)
        );

        listAnimals.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String selected = listAnimals.getSelectionModel().getSelectedItem();
                if (selected != null && !selected.equals("No animals in this enclosure"))
                    openAnimalView(findAnimalByName(selected));
            }
        });
    }

    public void setEnclosure(EnclosureCollection enclosure) {
        this.currentEnclosure = (Enclosure) enclosure;
        updateView();
    }

    private void updateView() {
        listAnimals.getItems().clear();
        if (currentEnclosure == null || currentEnclosure.getAnimals().isEmpty()) {
            listAnimals.getItems().add("No animals in this enclosure");
        } else {
            currentEnclosure.getAnimals().forEach(a -> listAnimals.getItems().add(a.getName()));
        }
    }

    private void openAnimalView(Animal animal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/zoomanagementsystem/animal-view.fxml"));
            Scene scene = new Scene(loader.load());
            AnimalViewController controller = loader.getController();
            controller.setAnimal(animal);

            if (animal == null && currentEnclosure != null) {
                String enclosureName = currentEnclosure.getName().toLowerCase();
                if (enclosureName.contains("lion")) {
                    controller.setAnimal(new Lion("", 0));
                } else if (enclosureName.contains("tiger")) {
                    controller.setAnimal(new Tiger("", 0));
                } else if (enclosureName.contains("cougar")) {
                    controller.setAnimal(new Cougar("", 0));
                }
            }
            Stage stage = new Stage();
            stage.setTitle(animal == null ? "Add New Animal" : "Edit Animal: " + animal.getName());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(listAnimals.getScene().getWindow());
            stage.showAndWait();

            Optional.ofNullable(controller.getAnimal()).ifPresent(newAnimal -> {
                if (isAnimalTypeValidForEnclosure(newAnimal)) {
                    if (animal != null) currentEnclosure.removeAnimal(animal);
                    currentEnclosure.addAnimal(newAnimal);
                    updateView();
                } else {
                    showAlert("Invalid Animal Type",
                            "Cannot add a " + newAnimal.getClass().getSimpleName() +
                                    " to " + currentEnclosure.getName() + ".");
                }
            });

        } catch (IOException e) {
            showAlert("Error", "Could not load Animal View: " + e.getMessage());
        }
    }

    private void handleDelete() {
        String selectedName = listAnimals.getSelectionModel().getSelectedItem();
        Animal animal = findAnimalByName(selectedName);

        if (animal != null) {
            currentEnclosure.removeAnimal(animal);
            updateView();
            showAlert("Animal Removed", selectedName + " has been removed from " + currentEnclosure.getName());
        }
    }

    private Animal findAnimalByName(String name) {
        return currentEnclosure.getAnimals().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }

    private boolean isAnimalTypeValidForEnclosure(Animal animal) {
        return currentEnclosure.getName().toLowerCase().contains(
                animal.getClass().getSimpleName().toLowerCase()
        );
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
