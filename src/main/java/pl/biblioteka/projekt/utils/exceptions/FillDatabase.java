package pl.biblioteka.projekt.utils.exceptions;

import pl.biblioteka.projekt.database.dao.BookDao;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.database.models.Book;
import pl.biblioteka.projekt.database.models.Category;

import java.util.Date;

public class FillDatabase {
    public static void fillDatabase(){
        Category category1 = new Category();
        category1.setName("Komidia");
        Category category2 = new Category();
        category2.setName("Dramat");
        Category category3 = new Category();
        category3.setName("Horror");
        Category category4 = new Category();
        category4.setName("Sci-fi");

        Author author = new Author();
        author.setName("Terry");
        author.setSurname("Pretchett");
        Book book1 = new Book();
        book1.setCategory(category1);
        book1.setAuthor(author);
        book1.setTitle("Kolor magii");
        book1.setIsbn(9765132354123431l);
        book1.setRating(3);
        book1.setReleaseDate(new Date());
        book1.setAddedDate(new Date());

        Author author2 = new Author();
        author2.setName("Adami");
        author2.setSurname("Mickiewicz");
        Book book2 = new Book();
        book2.setCategory(category2);
        book2.setAuthor(author2);
        book2.setTitle("Pan Tadeusz");
        book2.setIsbn(12345698562l);
        book2.setRating(2);
        book2.setReleaseDate(new Date());
        book2.setAddedDate(new Date());

        Author author3 = new Author();
        author3.setName("George");
        author3.setSurname("R.R Martin");
        Book book3 = new Book();
        book3.setCategory(category3);
        book3.setAuthor(author3);
        book3.setTitle("Piesn lodu i ognia");
        book3.setIsbn(1264598743544l);
        book3.setRating(5);
        book3.setReleaseDate(new Date());
        book3.setAddedDate(new Date());

        Author author4 = new Author();
        author4.setName("Tomasz");
        author4.setSurname("Dukaj");
        Book book4 = new Book();
        book4.setCategory(category4);
        book4.setAuthor(author4);
        book4.setTitle("Inne piesni");
        book4.setIsbn(123456l);
        book4.setRating(5);
        book4.setReleaseDate(new Date());
        book4.setAddedDate(new Date());


        BookDao bookDao = new BookDao(DbManager.getConnectionSource());

        try {
            bookDao.creatOrUpdate(book1);
            bookDao.creatOrUpdate(book2);
            bookDao.creatOrUpdate(book3);
            bookDao.creatOrUpdate(book4);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
