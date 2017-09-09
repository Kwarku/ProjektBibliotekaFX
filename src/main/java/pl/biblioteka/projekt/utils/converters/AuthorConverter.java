package pl.biblioteka.projekt.utils.converters;

import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.modelFx.AuthorFx;

public class AuthorConverter {
    public static Author convertAuthorFxToAuthor(AuthorFx authorFx) {
        Author author = new Author();
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());

        return author;
    }

}
