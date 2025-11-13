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
import org.example.zoomanagementsystem.Model.EnclosureCollection;
import org.example.zoomanagementsystem.ZooApplication;

import java.io.IOException;
import java.util.List;

/**
 * @author Justin Bouffard
 */
public class EnclosureCollectionController {
    @FXML
    private ListView<EnclosureCollection> aEnclosureCollectionListView;

    public void setEnclosureCollectionView(List<EnclosureCollection> pEnclosureCollection) {
        if (pEnclosureCollection == null) return;

        aEnclosureCollectionListView.getItems().clear();

        aEnclosureCollectionListView.setItems(FXCollections.observableArrayList(pEnclosureCollection));
    }

    @FXML
    private void onOpenButtonClick(ActionEvent pEvent) {
        if (aEnclosureCollectionListView.getSelectionModel().getSelectedItem() != null) {
            openEnclosureCollection(getSelectedEnclosure(), pEvent);
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

    private void openEnclosureCollection(EnclosureCollection pEnclosure, ActionEvent pEvent) {
        try {
            Parent view;
            if (!pEnclosure.getCollections().isEmpty()) {
                FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("enclosure-collection-view.fxml"));
                view = fxmlLoader.load();
                EnclosureCollectionController controller = fxmlLoader.getController();
                controller.setEnclosureCollectionView(pEnclosure.getCollections());
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("enclosure-view.fxml"));
                view = fxmlLoader.load();
                EnclosureController controller = fxmlLoader.getController();
                controller.setEnclosure(pEnclosure);
            }

            Scene nextScene = new Scene(view, 500, 500);
            Stage nextStage = new Stage();
            nextStage.setScene(nextScene);
            nextStage.setTitle(pEnclosure.getName());
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
            nextStage.showAndWait();
        } catch (IOException e) {
            openAlert(Alert.AlertType.ERROR, "Error", "View loading failed", e.getMessage());
        }
    }

    private EnclosureCollection getSelectedEnclosure() {
        return aEnclosureCollectionListView.getSelectionModel().getSelectedItem();
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
