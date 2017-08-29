package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;



public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private void initialize(){
        topMenuButtonsController.setMainController(this);
    }

}
