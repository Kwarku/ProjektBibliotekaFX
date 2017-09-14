package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.biblioteka.projekt.modelFx.AuthorFx;
import pl.biblioteka.projekt.modelFx.BookModel;
import pl.biblioteka.projekt.modelFx.CategoryFx;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

public class EditBookController {


    @FXML
    private ComboBox<CategoryFx> categoryComboBoxEdit;

    @FXML
    private ComboBox<AuthorFx> authorComboBoxEdit;

    @FXML
    private TextField bookTitleTextFieldEdit;

    @FXML
    private TextArea bookDescriptionTextAreaEdit;

    @FXML
    private TextField bookIsbnTextFieldEdit;

    @FXML
    private Slider bookNoteSliderEdit;

    @FXML
    private DatePicker bookReleaseDatePickerEdit;

    private BookModel bookModelToEdit;

    @FXML
    private void initialize() {
        this.bookModelToEdit = new BookModel();
        try {
            this.bookModelToEdit.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bindings();
    }

    public void editBookOnAction() {
        try {
            this.bookModelToEdit.saveBookInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    void bindings() {
        //wypelnienie comboboxow lista z modelu
        this.categoryComboBoxEdit.setItems(this.bookModelToEdit.getCategoryFxObservableList());
        authorComboBoxEdit.setItems(this.bookModelToEdit.getAuthorFxObservableList());

        categoryComboBoxEdit.valueProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().categoryFxProperty());
        authorComboBoxEdit.valueProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().authorFxProperty());
        bookTitleTextFieldEdit.textProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().titleProperty());
        bookDescriptionTextAreaEdit.textProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().descriptionProperty());
        bookIsbnTextFieldEdit.textProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().isbnProperty());
        bookNoteSliderEdit.valueProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().ratingProperty());
        bookReleaseDatePickerEdit.valueProperty().bindBidirectional(this.bookModelToEdit.bookFxObjectPropertyProperty().get().releaseDateProperty());

    }

    BookModel getBookModel() {
        return bookModelToEdit;
    }

}
