package pl.biblioteka.projekt.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import pl.biblioteka.projekt.database.dao.CategoryDao;
import pl.biblioteka.projekt.database.models.Category;
import pl.biblioteka.projekt.utils.converters.CategoryConverter;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.util.List;

// obsluga logiki ktory jest warstwa posrednia miedzy javafx a baza danych
public class CategoryModel {


    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();
    private TreeItem<String> root = new TreeItem<>();

    // metoda ktora wypelni combobox category danymi z bazy danych
    public void init() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.queryForAll(Category.class);
        initCategoryList(categories);
        initRoot(categories);


    }

    /*uzupelnianie drzewa kategorii:
    * pobieramy wszystkie wpisy z tabeli kategorie, nastepnie dla kazdego pobranego itemu tworzymy nowy TreeItem jako
    * prosty string i przypisujemy mu nazwe kategorii po czym dodajemy go do calego roota,
    * Aby dodac liste ksiazek do tego drzewa nalezy podczas tworzenia kolejnego wpisu przejechac sie po kategoriach i dodac
    * ich ksiazki,
    * by lista sie ladnie odswiezala nalezy przed cala zabawa zyczyscic dzieci tej listy*/
    private void initRoot(List<Category> categories) {
        this.root.getChildren().clear();
        categories.forEach(c -> {
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());
            c.getBooks().forEach(b -> categoryItem.getChildren().add(new TreeItem<>(b.getTitle())));
            root.getChildren().add(categoryItem);
        });
    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = CategoryConverter.convertToCategoryFx(c);
            this.categoryList.add(categoryFx);
        });
    }

    public void deleteCategoryByID() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        // odnosi sie do metody z klasy abstrakcyjnej i usuwa obikt o podanym id z listy
        categoryDao.deleteByID(Category.class, category.getValue().getId());
        init();
    }

    public void saveCategoryInDataBase(String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        //jeszcze raz wywolujemy metode init ktora pobiera liste i odswierza niejako wypisane obiekty w comboboxie
        init();
    }

    // szuka obiektow po id nastepnie je zmienia nastepnie updateuje
    public void updateCategoryInDataBase() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category tempCategory = categoryDao.findByID(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);


        init();

    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }
}
