package pl.biblioteka.projekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {


    private MainController mainController;

    @FXML
    void openLibe() {
        System.out.println("openLibe");
    }

    @FXML
    void openList() {
        System.out.println("openList");
    }

    @FXML
    void openStat() {
        System.out.println("openStat");

    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
