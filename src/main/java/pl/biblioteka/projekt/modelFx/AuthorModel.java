package pl.biblioteka.projekt.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.biblioteka.projekt.database.dao.AuthorDao;
import pl.biblioteka.projekt.database.dao.BookDao;
import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.utils.converters.AuthorConverter;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());
    private ObjectProperty<AuthorFx> authorFxObjectPropertyEdit = new SimpleObjectProperty<>(new AuthorFx());

    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = AuthorConverter.convertToAuthorFx(author);
            this.authorFxObservableList.add(authorFx);
        });
    }

    public void saveAuthorInDataBase() throws ApplicationException {
        saveOrUpdate(this.getAuthorFxObjectProperty());
    }

    public void saveAuthorEditInDataBase() throws ApplicationException {
        saveOrUpdate(this.getAuthorFxObjectPropertyEdit());
    }

    public void deleteAuthorInDatabase() throws ApplicationException, SQLException {
        AuthorDao authorDao = new AuthorDao();
        authorDao.deleteByID(Author.class, this.getAuthorFxObjectPropertyEdit().getId());
        BookDao bookDao = new BookDao();
        bookDao.deleteColumnByName(Book.AUTHOR_ID, this.getAuthorFxObjectPropertyEdit().getId());
        this.init();

    }

    private void saveOrUpdate(AuthorFx authorFxObjectPropertyEdit) throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        Author author = AuthorConverter.convertToAuthor(authorFxObjectPropertyEdit);
        authorDao.creatOrUpdate(author);

        this.init();
    }


    private AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public AuthorFx getAuthorFxObjectPropertyEdit() {
        return authorFxObjectPropertyEdit.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyEditProperty() {
        return authorFxObjectPropertyEdit;
    }

    public void setAuthorFxObjectPropertyEdit(AuthorFx authorFxObjectPropertyEdit) {
        this.authorFxObjectPropertyEdit.set(authorFxObjectPropertyEdit);
    }
}
