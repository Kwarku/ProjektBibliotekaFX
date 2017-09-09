package pl.biblioteka.projekt.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.biblioteka.projekt.database.dao.AuthorDao;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.utils.converters.AuthorConverter;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

public class AuthorModel {
    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());

    public void saveAuthorInDataBase() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        Author author = AuthorConverter.convertAuthorFxToAuthor(this.getAuthorFxObjectProperty());
        authorDao.creatOrUpdate(author);

        DbManager.closeConnectionSource();
    }

    public AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }
}
