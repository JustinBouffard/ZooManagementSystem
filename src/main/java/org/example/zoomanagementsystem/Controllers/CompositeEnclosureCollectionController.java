package org.example.zoomanagementsystem.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.zoomanagementsystem.Model.CompositeEnclosureCollection;
import org.example.zoomanagementsystem.ZooApplication;

import java.io.IOException;
import java.util.List;

public class CompositeEnclosureCollectionController {
    @FXML
    private ListView<CompositeEnclosureCollection> compositeEnclosureListView;

    public void setCompositeEnclosureCollectionView(List<CompositeEnclosureCollection> compositeEnclosureCollection) {
        if (compositeEnclosureCollection == null) return;

        compositeEnclosureListView.getItems().clear();

        compositeEnclosureListView.setItems(FXCollections.observableArrayList(compositeEnclosureCollection));
    }

    @FXML
    private void onOpenButtonClick(ActionEvent pEvent) throws IOException {
        if (compositeEnclosureListView.getSelectionModel().getSelectedItem() != null) {
            openEnclosureWindow(getSelectedEnclosure(), pEvent);
        } else
            openAlert(Alert.AlertType.WARNING, "Warning", "Nothing selected!", "You need to select an area to open!");

    }

    @FXML
    private void onBackButtonClick(ActionEvent pEvent) {
        List<Window> windows = Window.getWindows().stream().filter(Window::isShowing).toList();

        if (windows.size() > 1) {
            Stage stage = (Stage) ((Node) pEvent.getSource()).getScene().getWindow();
            stage.close();
        } else
            openAlert(Alert.AlertType.ERROR, "Error", "Cannot go back", "This is the main window, you cannot go back");
    }

    @FXML
    private void onCloseButtonClick() {
        Platform.exit();
    }

    private void openEnclosureWindow(CompositeEnclosureCollection pEnclosure, ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("enclosure-view.fxml"));
        Parent view = fxmlLoader.load();
        EnclosureViewController newEnclosureViewController = fxmlLoader.getController();
        newEnclosureViewController.setEnclosure(pEnclosure);
        Scene nextScene = new Scene(view, 500, 500);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle(pEnclosure.getName());
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.showAndWait();
    }

    private CompositeEnclosureCollection getSelectedEnclosure() {
        return compositeEnclosureListView.getSelectionModel().getSelectedItem();
    }

    private Alert openAlert(Alert.AlertType pType, String pTitle, String pHeader, String pContent) {
        Alert alert = new Alert(pType);
        alert.setTitle(pTitle);
        alert.setHeaderText(pHeader);
        alert.setContentText(pContent);
        alert.showAndWait();
        return alert;
    }
}
