package pl.biblioteka.projekt.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.biblioteka.projekt.modelFx.CategoryFx;
import pl.biblioteka.projekt.modelFx.CategoryModel;

public class CategoryController {

    @FXML
    private TextField addCategoryTextField;

    @FXML
    private Button addCategoryButton;

    @FXML
    private ComboBox<CategoryFx> addCategoryComboBox;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        initBindings();
    }

    private void initBindings() {
        // binduje przycisk i wylacza go gdy pole tekstowe jest puste
        addCategoryButton.disableProperty().bind(addCategoryTextField.textProperty().isEmpty());
    }

    public void addCategoryOnAction(ActionEvent actionEvent) {
        categoryModel.saveCategoryInDataBase(addCategoryTextField.getText());
        addCategoryTextField.clear();
    }
}
