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

    @FXML
    private Button addButton;


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
        validation();


    }

    @FXML
    private void addBookOnAction() {
        try {
            this.bookModel.saveBookInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        cleanValues();


    }



    public void bindings() {

        //wypelnienie comboboxow lista z modelu
        this.categoryComboBox.setItems(this.bookModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.bookModel.getAuthorFxObservableList());

        this.categoryComboBox.valueProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().categoryFxProperty());
        this.authorComboBox.valueProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().authorFxProperty());
        this.bookTitleTextField.textProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().titleProperty());
        this.bookDescriptionTextArea.textProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().descriptionProperty());
        this.bookIsbnTextField.textProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().isbnProperty());
        this.bookNoteSlider.valueProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().ratingProperty());
        this.bookReleaseDatePicker.valueProperty().bindBidirectional(this.bookModel.bookFxObjectPropertyProperty().get().releaseDateProperty());


    }

    private void validation() {
        this.addButton.disableProperty().bind(this.bookIsbnTextField.textProperty().isEmpty()
                .or(this.bookIsbnTextField.textProperty().isEmpty())
                .or(this.authorComboBox.valueProperty().isNull())
                .or(this.categoryComboBox.valueProperty().isNull())
                .or(this.bookReleaseDatePicker.valueProperty().isNull()));
    }

    private void cleanValues() {
        this.authorComboBox.getSelectionModel().clearSelection();
        this.categoryComboBox.getSelectionModel().clearSelection();
        this.bookTitleTextField.clear();
        this.bookDescriptionTextArea.clear();
        this.bookIsbnTextField.clear();
        this.bookNoteSlider.setValue(1);
        this.bookReleaseDatePicker.getEditor().clear();

    }

    public BookModel getBookModel() {
        return bookModel;
    }
}
