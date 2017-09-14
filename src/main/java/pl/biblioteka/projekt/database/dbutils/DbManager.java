package pl.biblioteka.projekt.database.dbutils;


import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.database.models.Category;

import java.io.IOException;
import java.sql.SQLException;


public class DbManager {

    public static final String JDBC_DRIVER_HD = "jdbc:h2:./libraryDB";
    public static final String USER = "admin";
    public static final String PASS = "admin";
    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);
    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();       // tworzenie polaczanie
//        dropTable();                    // usuwanie tabel wszystkich
        createTable();                  // tworzenie nowych tabel
        closeConnectionSource();        // zamykanie polaczenia
    }

    // tworzy polaczaczenie z baza dancych, nalezy podac login i haslo
    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    // zwraca polaczaczenie
    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    // zamyka polaczenie z baza dancyh, jezeli wystepuje blad wyrzuca do logera
    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    // tworzy tabele jezeli nie sa utworzone
    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Author.class);
            TableUtils.createTableIfNotExists(connectionSource, Book.class);
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    // usuwa tabele
    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Author.class, true);
            TableUtils.dropTable(connectionSource, Book.class, true);
            TableUtils.dropTable(connectionSource, Category.class, true);

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

}
