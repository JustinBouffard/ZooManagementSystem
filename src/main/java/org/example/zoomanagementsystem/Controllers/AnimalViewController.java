package org.example.zoomanagementsystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.zoomanagementsystem.Model.*;

/**
 * @author Shanley
 */
public class AnimalViewController {
    @FXML
    private TextField animalTypeText;

    @FXML
    private TextField animalNameText;

    @FXML
    private TextField animalAgeText;

    private Animal aResultAnimal;
    private Animal aOriginalAnimal;

    public void setAnimal (Animal pAnimal) {
        this.aOriginalAnimal = pAnimal;

        if (pAnimal != null) {
            animalTypeText.setEditable(false);
            animalTypeText.setFocusTraversable(false);
            animalTypeText.setMouseTransparent(true);
            animalTypeText.setText(pAnimal.getClass().getSimpleName());
            animalNameText.setText(pAnimal.getName());
            animalAgeText.setText(String.valueOf(pAnimal.getAge()));
        }
        else {
            animalTypeText.setEditable(true);
            animalTypeText.clear();
            animalNameText.clear();
            animalAgeText.clear();
        }
    }

    @FXML
    public void onSaveButtonClick(ActionEvent actionEvent) {
        String pType = animalTypeText.getText().trim();
        String pName = animalNameText.getText().trim();
        String pAgeStr = animalAgeText.getText().trim();

        if (pType.isEmpty() || pName.isEmpty() || pAgeStr.isEmpty()) {
            showAlert("Please fill all the fields");
            return;
        }

        double pAge;
        try {
            pAge = Double.parseDouble(pAgeStr);
            if (pAge < 0) {
                showAlert("Please enter a valid number");
                return;
            }
        }
        catch (NumberFormatException e) {
            showAlert("Please enter a valid number");
            return;
        }

        Animal newAnimal;
        if (pType.equalsIgnoreCase("lion")) {
            newAnimal = new Lion(pName, pAge);
        }
        else if (pType.equalsIgnoreCase("tiger")) {
            newAnimal = new Tiger(pName, pAge);
        }
        else if  (pType.equalsIgnoreCase("cougar")) {
            newAnimal = new Cougar(pName, pAge);
        }
        else {
            showAlert("Please enter a valid animal type");
            return;
        }

        aResultAnimal = newAnimal;
        closeWindow();
    }


    public void onCancelButtonClick(ActionEvent actionEvent) {
        aResultAnimal = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) animalTypeText.getScene().getWindow();
        stage.close();
    }

    public Animal getAnimal() {
        return aResultAnimal;
    }

    public Animal getOriginalAnimal() {
        return aOriginalAnimal;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}