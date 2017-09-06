package pl.biblioteka.projekt.modelFx;

import com.j256.ormlite.dao.DaoManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.biblioteka.projekt.database.dao.CategoryDao;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.database.models.Category;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.util.List;

// obsluga logiki ktory jest warstwa posrednia miedzy javafx a baza danych
public class CategoryModel {


    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();

    // metoda ktora wypelni combobox category danymi z bazy danych
    public void init() throws ApplicationException {
        //polaczenie z baza danych
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());

        // lista obiektow z tabeli kategorie
        List<Category> categories = categoryDao.queryForAll(Category.class);

        //czyszczenie listy aby przy odswierzeniu nie dublowaly sie rekordy
        this.categoryList.clear();

        // dla kazdego elementu listy tworzy obiekt categoryfx i wypelnia go danymi z bazy dancyh i dodaje do categoryList
        categories.forEach(c->{
            CategoryFx categoryFx = new CategoryFx();
            categoryFx.setId(c.getId());
            categoryFx.setName(c.getName());
            this.categoryList.add(categoryFx);
        });
        //zamyka polaczenie z baza danych
        DbManager.closeConnectionSource();


    }

    public void deleteCategoryByID() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        // odnosi sie do metody z klasy abstrakcyjnej i usuwa obikt o podanym id z listy
        categoryDao.deleteByID(Category.class, category.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public void saveCategoryInDataBase(String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
        //jeszcze raz wywolujemy metode init ktora pobiera liste i odswierza niejako wypisane obiekty w comboboxie
        init();
    }

    // szuka obiektow po id nastepnie je zmienia nastepnie updateuje
    public void updateCategoryInDataBase() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category tempCategory = categoryDao.findByID(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);


        DbManager.closeConnectionSource();
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

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }
}
