package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.biblioteka.projekt.modelFx.AuthorFx;
import pl.biblioteka.projekt.modelFx.AuthorModel;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class AuthorController {

    @FXML
    private TextField authorNameTextField;

    @FXML
    private TextField authorSurnameTextField;

    @FXML
    private Button addAuthorButton;

    @FXML
    private TableView<AuthorFx> authorTableView;

    @FXML
    private TableColumn<AuthorFx, String> nameColumn;

    @FXML
    private TableColumn<AuthorFx, String> surnameColumn;

    @FXML
    private MenuItem deleteAuthorMenuItem;

    private AuthorModel authorModel;

    @FXML
    private void initialize() {
        this.authorModel = new AuthorModel();
        try {
            this.authorModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }


        bindings();
        bindingsTableView();


    }

    private void bindingsTableView() {
        authorTableView.setItems(this.authorModel.getAuthorFxObservableList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                this.authorModel.setAuthorFxObjectPropertyEdit(newValue));
    }

    private void bindings() {
        this.authorModel.authorFxObjectPropertyProperty().get().nameProperty().bind(this.authorNameTextField.textProperty());
        this.authorModel.authorFxObjectPropertyProperty().get().surnameProperty().bind(this.authorSurnameTextField.textProperty());

        this.addAuthorButton.disableProperty().bind(this.authorNameTextField.textProperty().isEmpty().or(this.authorSurnameTextField.textProperty().isEmpty()));
        this.deleteAuthorMenuItem.disableProperty().bind(this.authorTableView.getSelectionModel().selectedItemProperty().isNull());
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


    public void onEditCommitName(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorFxObjectPropertyEdit().setName(authorFxStringCellEditEvent.getNewValue());
        updateInDatabase();

    }

    public void onEditCommitSurname(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorFxObjectPropertyEdit().setSurname(authorFxStringCellEditEvent.getNewValue());
        updateInDatabase();

    }

    private void updateInDatabase() {
        try {
            this.authorModel.saveAuthorEditInDataBase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteAuthorOnAction() {
        try {
            this.authorModel.deleteAuthorInDatabase();
        } catch (ApplicationException | SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}

