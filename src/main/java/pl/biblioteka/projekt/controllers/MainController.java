package pl.biblioteka.projekt.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.FxmlUtils;

import java.util.Optional;


public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private void initialize() {
        topMenuButtonsController.setMainController(this);
    }

    void setCenter(String fxmlPath) {

        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));

    }

    @FXML
    public void closeApplication() {
        Optional<ButtonType> result = DialogUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }

    }

    @FXML
    public void setModena() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    @FXML
    public void setCaspian() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    @FXML
    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    @FXML
    public void about() {
        DialogUtils.dialogAboutApplication();
    }
}
