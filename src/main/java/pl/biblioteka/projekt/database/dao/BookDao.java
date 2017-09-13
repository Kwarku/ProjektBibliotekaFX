package pl.biblioteka.projekt.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class BookDao extends CommonDao {
    public BookDao() {
        super();
    }


    public void deleteColumnByName(String columnName, int id) throws ApplicationException, SQLException {
        Dao<Book, Object> dao = getDao(Book.class);
        DeleteBuilder<Book, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columnName, id);
        dao.delete(deleteBuilder.prepare());

    }
}
