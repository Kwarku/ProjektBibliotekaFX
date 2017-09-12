package pl.biblioteka.projekt.modelFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.biblioteka.projekt.database.dao.BookDao;
import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.utils.converters.BookConverter;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.util.List;

public class BookListModel {

    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book -> this.bookFxObservableList.add(BookConverter.convertToBookFx(book)));

    }

    public ObservableList<BookFx> getBookFxObservableList() {
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }
}
