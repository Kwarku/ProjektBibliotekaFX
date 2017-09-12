package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.biblioteka.projekt.modelFx.AuthorFx;
import pl.biblioteka.projekt.modelFx.BookFx;
import pl.biblioteka.projekt.modelFx.BookListModel;
import pl.biblioteka.projekt.modelFx.CategoryFx;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.time.LocalDate;

public class BookListController {

    @FXML
    private TableView<BookFx> booksTableView;

    @FXML
    private TableColumn<BookFx, String> titleColumn;

    @FXML
    private TableColumn<BookFx, String> descColumn;

    @FXML
    private TableColumn<BookFx, AuthorFx> authorColumn;

    @FXML
    private TableColumn<BookFx, CategoryFx> categoryColumn;

    @FXML
    private TableColumn<BookFx, Number> ratingColumn;

    @FXML
    private TableColumn<BookFx, String> isbnColumn;

    @FXML
    private TableColumn<BookFx, LocalDate> releaseDateColumn;

    private BookListModel bookListModel;

    @FXML
    private void initialize() {
        this.bookListModel = new BookListModel();
        try {
            this.bookListModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        /*bindowanie z bazdy dancyh do wyswietlania w table View */

        this.booksTableView.setItems(this.bookListModel.getBookFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        this.releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());

    }
}
