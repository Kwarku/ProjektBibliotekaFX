package pl.biblioteka.projekt.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {


    public static final String LIBRARY_FXML = "/fxml/Library.fxml";
    public static final String BOOK_LIST_FXML = "/fxml/BookList.fxml";
    public static final String STATISTICS_FXML = "/fxml/Statistics.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String ADD_CATEGOTY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void openLibe() {
        mainController.setCenter(LIBRARY_FXML);
    }

    @FXML
    public void openList() {
        mainController.setCenter(BOOK_LIST_FXML);
    }

    @FXML
    public void openStat() {
        mainController.setCenter(STATISTICS_FXML);

    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addBook() {
        //ta metoda wylacza nam cala grupe toogle buttonow ktore sa wstrzykniete tam wyrzej xD
        resetToggleButtons();

        mainController.setCenter(ADD_BOOK_FXML);
    }


    public void addCategory() {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGOTY_FXML);
    }

    // metoda resetuje przyciski typu toggle, zostala dodana bo nie powinno sie uzywac 2 razy tego samego kodu
    private void resetToggleButtons() {
        if (toggleButtons.getSelectedToggle() != null) {
            toggleButtons.getSelectedToggle().setSelected(false);
        }
    }

    public void addAuthor() {
        resetToggleButtons();
        mainController.setCenter(ADD_AUTHOR_FXML);
    }
}
