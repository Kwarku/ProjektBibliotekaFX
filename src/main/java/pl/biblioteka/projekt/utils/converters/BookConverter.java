package pl.biblioteka.projekt.utils.converters;

import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.modelFx.BookFx;
import pl.biblioteka.projekt.utils.Utils;

public class BookConverter {

    public static Book convertToBook(BookFx bookFx) {
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setDescription(bookFx.getDescription());
        book.setRating(bookFx.getRating());
        book.setIsbn(bookFx.getIsbn());
        book.setReleaseDate(Utils.convertToDate(bookFx.getReleaseDate()));
        book.setAddedDate(Utils.convertToDate(bookFx.getAddedDate()));

        return book;
    }

    public static BookFx convertToBookFx(Book book) {
        BookFx bookFx = new BookFx();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        bookFx.setDescription(book.getDescription());
        bookFx.setRating(book.getRating());
        bookFx.setIsbn(book.getIsbn());
        bookFx.setReleaseDate(Utils.converteToLocalDate(book.getReleaseDate()));
        bookFx.setAuthorFx(AuthorConverter.convertToAuthorFx(book.getAuthor()));
        bookFx.setCategoryFx(CategoryConverter.convertToCategoryFx(book.getCategory()));

        return bookFx;
    }
}
