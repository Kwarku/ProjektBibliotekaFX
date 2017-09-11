package pl.biblioteka.projekt.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.biblioteka.projekt.database.dao.AuthorDao;
import pl.biblioteka.projekt.database.dao.BookDao;
import pl.biblioteka.projekt.database.dao.CategoryDao;
import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.database.models.Category;
import pl.biblioteka.projekt.utils.converters.AuthorConverter;
import pl.biblioteka.projekt.utils.converters.BookConverter;
import pl.biblioteka.projekt.utils.converters.CategoryConverter;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.util.List;


public class BookModel {

    private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());

    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationException {
        initAuthorList();
        initCategoryList();

    }

    public void saveBookInDatabase() throws ApplicationException {
        //uzywamy konwertera ksiazki do zapiasnia ksiazki do bazy danych
        Book book = BookConverter.convertToBook(this.getBookFxObjectProperty());

        // powolujemy nowy obiekt category dao nastepnie mu ksiazki z categorii bazodanowej
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findByID(Category.class, this.getBookFxObjectProperty().getCategoryFx().getId());
        book.setCategory(category);

        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.findByID(Author.class, this.getBookFxObjectProperty().getAuthorFx().getId());
        book.setAuthor(author);

        // stworznie ksiazki w bazie dancyh
        BookDao bookDao = new BookDao();
        bookDao.creatOrUpdate(book);

    }

    private void initCategoryList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFxObservableList.clear();
        categoryList.forEach(category -> {
            CategoryFx categoryFx = CategoryConverter.convertToCategoryFx(category);
            categoryFxObservableList.add(categoryFx);
        });

    }

    private void initAuthorList() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = AuthorConverter.convertToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });


    }

    public BookFx getBookFxObjectProperty() {
        return bookFxObjectProperty.get();
    }

    public ObjectProperty<BookFx> bookFxObjectPropertyProperty() {
        return bookFxObjectProperty;
    }

    public void setBookFxObjectProperty(BookFx bookFxObjectProperty) {
        this.bookFxObjectProperty.set(bookFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
