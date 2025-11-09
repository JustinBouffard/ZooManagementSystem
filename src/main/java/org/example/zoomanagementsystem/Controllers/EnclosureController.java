package org.example.zoomanagementsystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.zoomanagementsystem.Model.Animal;
import org.example.zoomanagementsystem.Model.Enclosure;

import java.io.IOException;

/**
 * @author Pascale Fontaine
 */
public class EnclosureController {

    @FXML
    private Label lblEnclosureName;

    @FXML
    private ListView<String> listAnimals;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClose;

    // The enclosure being displayed (uses EnclosureCollection interface)
    private Enclosure currentEnclosure;


    @FXML
    public void initialize() {
        // Set up button actions
        btnAdd.setOnAction(event -> handleAdd());
        btnDelete.setOnAction(event -> handleDelete());
        btnBack.setOnAction(event -> handleBack());
        btnClose.setOnAction(event -> handleClose());

        // Initially disable delete button at start
        btnDelete.setDisable(true);

        // Enable/disable delete button based on selection in ListView
        listAnimals.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    btnDelete.setDisable(newValue == null);
                }
        );

        // double-click listener to edit an animal
        listAnimals.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleEdit();
            }
        });
    }


    public void setEnclosure(Enclosure enclosure) {
        this.currentEnclosure = enclosure;
        updateView();
    }

    private void updateView() {
        if (currentEnclosure == null) {
            return;
        }

        // Update enclosure name label
        lblEnclosureName.setText("Enclosure: " + currentEnclosure.getName());

        // Clear the list
        listAnimals.getItems().clear();

        // show the animals
        if (currentEnclosure.getAnimals().isEmpty()) {
            listAnimals.getItems().add("No animals in this enclosure");
        } else {
            for (Animal animal : currentEnclosure.getAnimals()) {
                // Display only the animal name (no age information)
                listAnimals.getItems().add(animal.getName());
            }
        }
    }


    @FXML
    private void handleAdd() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/org/example/zoomanagementsystem/animal-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Get AnimalViewController and set it up for adding a new animal
            //might change it

            Stage stage = new Stage();
            stage.setTitle("Add New Animal");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());
            stage.showAndWait();

            // Refresh the view after the dialog closes (if animal is added)
            updateView();

        } catch (IOException e) {
            showAlert("Error", "Could not load Animal View: " + e.getMessage());
        }
    }

    private void handleEdit() {
        String selectedAnimalName = listAnimals.getSelectionModel().getSelectedItem();

        if (selectedAnimalName == null || selectedAnimalName.equals("No animals in this enclosure")) {
            return;
        }

        // Find the selected animal
        Animal selectedAnimal = null;
        for (Animal animal : currentEnclosure.getAnimals()) {
            if (animal.getName().equals(selectedAnimalName)) {
                selectedAnimal = animal;
                break;
            }
        }

        if (selectedAnimal == null) {
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/org/example/zoomanagementsystem/animal-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Get the AnimalViewController and set it up for editing an existing animal
            // might also change it

            Stage stage = new Stage();
            stage.setTitle("Edit Animal: " + selectedAnimal.getName());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(listAnimals.getScene().getWindow());
            stage.showAndWait();

            // Refresh the view after the dialog closes
            updateView();

        } catch (IOException e) {
            showAlert("Error", "Could not load Animal View: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        String selectedAnimalName = listAnimals.getSelectionModel().getSelectedItem();

        if (selectedAnimalName == null || selectedAnimalName.equals("No animals in this enclosure")) {
            showAlert("No Selection", "Please select an animal to delete.");
            return;
        }

        // Find and remove the animal from the enclosure
        Animal animalToRemove = null;
        for (Animal animal : currentEnclosure.getAnimals()) {
            if (animal.getName().equals(selectedAnimalName)) {
                animalToRemove = animal;
                break;
            }
        }

        if (animalToRemove != null) {
            currentEnclosure.removeAnimal(animalToRemove);
            updateView();
            showAlert("Animal Removed", selectedAnimalName + " has been removed from " + currentEnclosure.getName());
        }
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleClose() {
        System.exit(0);
    }

    /**
     * Displays an alert dialog.
     * @param title  error
     * @param message error
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Enclosure getCurrentEnclosure() {
        return currentEnclosure;
    }
}