package pl.biblioteka.projekt.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.biblioteka.projekt.modelFx.CategoryFx;
import pl.biblioteka.projekt.modelFx.CategoryModel;
import pl.biblioteka.projekt.utils.DialogUtils;

public class CategoryController {

    @FXML
    private TextField addCategoryTextField;

    @FXML
    private Button addCategoryButton;

    @FXML
    private ComboBox<CategoryFx> addCategoryComboBox;

    @FXML
    private Button editCategoryButton;

    @FXML
    private Button delCategoryButton;

    private CategoryModel categoryModel;

    // metoda initialize jest pierwsza metoda po konstruktorze wykonywana
    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        // przypisanie do comboBoxa calej zawartosi listy z danymi z bazy danych
        this.addCategoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
    }

    // binduje przycisk i wylacza go gdy pole tekstowe jest puste
    private void initBindings() {
        this.addCategoryButton.disableProperty().bind(this.addCategoryTextField.textProperty().isEmpty());
        this.delCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        this.editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    public void addCategoryOnAction( ) {
        categoryModel.saveCategoryInDataBase(addCategoryTextField.getText());
        addCategoryTextField.clear();
    }

    public void deleteCategoryOnAction() {
        categoryModel.deleteCategoryByID();
    }

    //metoda ktora po wcisnieciu comboBoxa przechwytuje obiekt wyswietlony i przypisauje go do categoryModel aby mozna bylo nim sterowac
    public void comboBoxOnAction() {
        this.categoryModel.setCategory(this.addCategoryComboBox.getSelectionModel().getSelectedItem());

    }

    public void editCategoryOnAction() {
        String newCategoryName = DialogUtils.editDialog(this.categoryModel.getCategory().getName());

        if (newCategoryName != null){
            this.categoryModel.getCategory().setName(newCategoryName);
            this.categoryModel.updateCategoryInDataBase();
        }
    }
}