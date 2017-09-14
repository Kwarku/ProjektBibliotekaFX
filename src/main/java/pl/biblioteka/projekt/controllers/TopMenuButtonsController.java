package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {


    public static final String BOOK_LIST_FXML = "/fxml/BookList.fxml";
    private static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    private static final String ADD_CATEGOTY_FXML = "/fxml/AddCategory.fxml";
    private static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void openList() {
        mainController.setCenter(BOOK_LIST_FXML);
    }

    @FXML
    public void addBook() {
        //ta metoda wylacza nam cala grupe toogle buttonow ktore sa wstrzykniete tam wyrzej xD
        resetToggleButtons();

        mainController.setCenter(ADD_BOOK_FXML);
    }

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void resetToggleButtons() {
        if (toggleButtons.getSelectedToggle() != null) {
            toggleButtons.getSelectedToggle().setSelected(false);
        }
    }

    public void addCategory() {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGOTY_FXML);
    }

    public void addAuthor() {
        resetToggleButtons();
        mainController.setCenter(ADD_AUTHOR_FXML);
    }
}
