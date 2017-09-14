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

    public static final String BRIGHT_THEME_CSS = "style/brightTheme.css";
    private static final String DARK_THEME_CSS = "style/DarkTheme.css";
    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private void initialize() {
        topMenuButtonsController.setMainController(this);
        setCenter(TopMenuButtonsController.BOOK_LIST_FXML);
    }

    void setCenter(String fxmlPath) {

        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));

    }

    @FXML
    private void closeApplication() {
        Optional<ButtonType> result = DialogUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }

    }

    @FXML
    private void setModena() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        addStyle(BRIGHT_THEME_CSS);
    }

    @FXML
    private void setCaspian() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        addStyle(DARK_THEME_CSS);
    }

    private void addStyle(String path) {
        borderPane.getScene().getStylesheets().clear();
        borderPane.getScene().getStylesheets().add(path);
    }

    @FXML
    private void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    @FXML
    private void about() {
        DialogUtils.dialogAboutApplication();
    }


}
