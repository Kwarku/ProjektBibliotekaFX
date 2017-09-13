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
    public Button addButton;

    @FXML
    public Button editButton;


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
    private void addBookOnAction() {
        try {
            this.bookModel.saveBookInDatabase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

//        cleanValues();


    }

    @FXML
    private void editBookOnAction() {
        System.out.println("edytuje ksaizke");
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


       /*  zmiana bindowania z jednostronnego na dwustronne
        //bindowanie elementeow z bookModel i bookfx
        this.bookModel.bookFxObjectPropertyProperty().get().authorFxProperty().bind(this.authorComboBox.valueProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().categoryFxProperty().bind(this.categoryComboBox.valueProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().titleProperty().bind(this.bookTitleTextField.textProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().descriptionProperty().bind(this.bookDescriptionTextArea.textProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().isbnProperty().bind(this.bookIsbnTextField.textProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().ratingProperty().bind(this.bookNoteSlider.valueProperty());
        this.bookModel.bookFxObjectPropertyProperty().get().releaseDateProperty().bind(this.bookReleaseDatePicker.valueProperty());
    */
    }

    private void blockButtonClick() {
        this.addButton.disableProperty().bind(this.bookIsbnTextField.textProperty().isEmpty()
                .or(this.bookIsbnTextField.textProperty().isEmpty())
                .or(this.authorComboBox.valueProperty().isNull())
                .or(this.categoryComboBox.valueProperty().isNull())
                .or(this.bookReleaseDatePicker.valueProperty().isNull()));
    }

    public void cleanValues() {
        authorComboBox.setValue(null);
        categoryComboBox.setValue(null);
        bookTitleTextField.clear();
        bookDescriptionTextArea.clear();
        bookIsbnTextField.clear();
        bookNoteSlider.setValue(bookNoteSlider.getMin());
        bookReleaseDatePicker.setValue(null);

    }

    public BookModel getBookModel() {
        return bookModel;
    }
}
