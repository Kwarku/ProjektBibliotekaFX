package pl.biblioteka.projekt.database.dao;

import com.j256.ormlite.support.ConnectionSource;

public class BookDao extends CommonDao {
    public BookDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }
}
