package pl.biblioteka.projekt.utils.converters;

import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.modelFx.BookFx;
import pl.biblioteka.projekt.utils.Utils;

public class BookConverter {

    public static Book convertToBook(BookFx bookFx){
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setDescription(bookFx.getDescription());
        book.setRating(bookFx.getNote());
        book.setIsbn(bookFx.getIsbn());
        book.setReleaseDate(Utils.convertToDate(bookFx.getReleaseDate()));
        book.setAddedDate(Utils.convertToDate(bookFx.getAddedDate()));

        return book;
    }
}
