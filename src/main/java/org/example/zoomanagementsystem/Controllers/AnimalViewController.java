package org.example.zoomanagementsystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.zoomanagementsystem.Model.*;

public class AnimalViewController {
    @FXML
    private TextField animalTypeText;

    @FXML
    private TextField animalNameText;

    @FXML
    private TextField animalAgeText;

    @FXML
    private Button animalSaveButton;

    @FXML
    private Button animalCancelButton;

    private Animal aResultAnimal;
    private Animal aOriginalAnimal;

    public void setAnimal (Animal pAnimal) {
        this.aOriginalAnimal = pAnimal;

        if (pAnimal != null) {
            animalTypeText.setEditable(false);
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
            System.out.println("Please fill all the fields");
            return;
        }

        double pAge;
        try {
            pAge = Double.parseDouble(pAgeStr);
            if (pAge < 0) {
                System.out.println("Please enter a valid number");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
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
            System.out.println("Please enter a valid animal type");
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
        Stage stage = (Stage) animalSaveButton.getScene().getWindow();
        stage.close();
    }

    public Animal getAnimal() {
        return aResultAnimal;
    }

    public Animal getOriginalAnimal() {
        return aOriginalAnimal;
    }
}
