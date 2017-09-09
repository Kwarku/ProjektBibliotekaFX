package pl.biblioteka.projekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.biblioteka.projekt.modelFx.AuthorModel;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

public class AuthorController {

    @FXML
    private TextField authorNameTextField;

    @FXML
    private TextField authorSurnameTextField;

    @FXML
    private Button addAuthorButton;

    private AuthorModel authorModel;

    @FXML
    private void initialize() {
        this.authorModel = new AuthorModel();

        // bindowanie tkestu z pola tekstowego do modelu polaczonego z baza dancyh
        this.authorModel.authorFxObjectPropertyProperty().get().nameProperty().bind(this.authorNameTextField.textProperty());
        this.authorModel.authorFxObjectPropertyProperty().get().surnameProperty().bind(this.authorSurnameTextField.textProperty());

        this.addAuthorButton.disableProperty().bind(this.authorNameTextField.textProperty().isEmpty().or(this.authorSurnameTextField.textProperty().isEmpty()));
    }

    @FXML
    void addAuthorOnAction() {

        try {
            this.authorModel.saveAuthorInDataBase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        this.authorSurnameTextField.clear();
        this.authorNameTextField.clear();
    }


}

