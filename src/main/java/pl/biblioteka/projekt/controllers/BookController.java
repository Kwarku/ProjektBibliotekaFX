package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.biblioteka.projekt.modelFx.AuthorFx;
import pl.biblioteka.projekt.modelFx.BookModel;
import pl.biblioteka.projekt.modelFx.CategoryFx;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

public class BookController {

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    @FXML
    private ComboBox<AuthorFx> authorComboBox;

    @FXML
    private TextField bookTitleTextField;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private TextField bookIsbnTextField;

    @FXML
    private Slider bookNoteSlider;

    @FXML
    private DatePicker bookReleaseDatePicker;

    private BookModel bookModel;

    @FXML
    private void initialize() {
        this.bookModel = new BookModel();
        try {
            this.bookModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bindings();


    }

    private void bindings() {
        //wypelnienie comboboxow lista z modelu
        this.categoryComboBox.setItems(this.bookModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.bookModel.getAuthorFxObservableList());
        //bindowanie elementeow z bookModel i bookfx
        this.bookModel.bookFxObjectPropertyProperty().get().authorFxProperty().bind(this.authorComboBox.valueProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().categoryFxProperty().bind(this.categoryComboBox.valueProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().titleProperty().bind(bookTitleTextField.textProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().descriptionProperty().bind(this.bookDescriptionTextArea.textProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().isbnProperty().bind(this.bookIsbnTextField.textProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().ratingProperty().bind(this.bookNoteSlider.valueProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().releaseDateProperty().bind(this.bookReleaseDatePicker.valueProperty());
    }

    @FXML
    void addBookOnAction() {
        try {
            this.bookModel.saveBookInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }

}
