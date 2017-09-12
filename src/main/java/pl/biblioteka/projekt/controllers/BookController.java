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
    private Button addButton;

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
        blockButtonClick();
    }

    @FXML
    void addBookOnAction() {
        try {
            this.bookModel.saveBookInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        cleanValues();



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

    private void blockButtonClick() {
        this.addButton.disableProperty().bind(this.bookIsbnTextField.textProperty().isEmpty()
                .or(this.bookIsbnTextField.textProperty().isEmpty())
                .or(this.authorComboBox.valueProperty().isNull())
                .or(this.categoryComboBox.valueProperty().isNull())
                .or(this.bookReleaseDatePicker.valueProperty().isNull()));
    }

    private void cleanValues() {
        authorComboBox.setValue(null);
        categoryComboBox.setValue(null);
        bookTitleTextField.clear();
        bookDescriptionTextArea.clear();
        bookIsbnTextField.clear();
        bookNoteSlider.setValue(bookNoteSlider.getMin());
        bookReleaseDatePicker.setValue(null);
    }

}
