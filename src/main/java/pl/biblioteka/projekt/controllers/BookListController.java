package pl.biblioteka.projekt.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.biblioteka.projekt.modelFx.AuthorFx;
import pl.biblioteka.projekt.modelFx.BookFx;
import pl.biblioteka.projekt.modelFx.BookListModel;
import pl.biblioteka.projekt.modelFx.CategoryFx;
import pl.biblioteka.projekt.utils.DialogUtils;
import pl.biblioteka.projekt.utils.FxmlUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.time.LocalDate;

public class BookListController {


    private static final String ICONS_DELETE_PNG = "/icons/delete.png";
    private static final String ICONS_EDIT_PNG = "/icons/edit.png";

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    @FXML
    private ComboBox<AuthorFx> authorComboBox;


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

    @FXML
    private TableColumn<BookFx, BookFx> deleteColumn;

    @FXML
    private TableColumn<BookFx, BookFx> editColumn;


    private BookListModel bookListModel;

    @FXML
    private void initialize() {
        this.bookListModel = new BookListModel();
        try {
            this.bookListModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }


        this.categoryComboBox.setItems(this.bookListModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.bookListModel.getAuthorFxObservableList());

        //wybranie czegos z combo boxa jest to zapisywane od razu w
        this.bookListModel.categoryFxObjectPropertyProperty().bind(this.categoryComboBox.valueProperty());
        this.bookListModel.authorFxObjectPropertyProperty().bind(this.authorComboBox.valueProperty());

        /*bindowanie z bazdy dancyh do wyswietlania w table View */
        this.booksTableView.setItems(this.bookListModel.getBookFxObservableList());

        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        this.releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());

        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.deleteColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {
            Button button = createButton(ICONS_DELETE_PNG);

            @Override
            protected void updateItem(BookFx item, boolean empty) {
                super.updateItem(item, empty);
                //jezeli jest pusta to nie ustawia przycisku -> potrzebne w odswiezaniu listy
                if (empty) {
                    setGraphic(null);
                    return;
                }

                //jezeli komorka nie jest pust to tworzy nowy przycisk
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            bookListModel.deleteBook(item);
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });


        this.editColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {
            Button button = createButton(ICONS_EDIT_PNG);

            @Override
            protected void updateItem(BookFx item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }


                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddBook.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                        BookController controller = loader.getController();
                        controller.getBookModel().setBookFxObjectProperty(item);
                        controller.bindings();

                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();

                        try {
                            bookListModel.init();
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });

    }

    private Button createButton(String path) {
        Button button = new Button();
        Image image = new Image(this.getClass().getResource(path).toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }

    public void filterOnActionComboBox() {
        this.bookListModel.filterBookList();

    }

    public void clearCategoryComboBox() {
        this.categoryComboBox.getSelectionModel().clearSelection();

    }

    public void clearAuthorComboBox() {
        this.authorComboBox.getSelectionModel().clearSelection();
    }
}
