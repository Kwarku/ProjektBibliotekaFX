package pl.biblioteka.projekt.modelFx;

import com.j256.ormlite.dao.DaoManager;
import pl.biblioteka.projekt.database.dao.CategoryDao;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.database.models.Category;

// obsluga logiki ktory jest warstwa posrednia miedzy javafx a baza danych
public class CategoryModel {


    public void saveCategoryInDataBase(String name) {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
    }


}
