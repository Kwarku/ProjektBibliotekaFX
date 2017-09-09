package pl.biblioteka.projekt.utils.converters;

        import pl.biblioteka.projekt.database.models.Author;
        import pl.biblioteka.projekt.modelFx.AuthorFx;

public class AuthorConverter {
    public static Author convertToAuthor(AuthorFx authorFx) {
        Author author = new Author();
        author.setId(authorFx.getId());
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());

        return author;
    }

    public static AuthorFx convertToAuthorFx(Author author){
        AuthorFx authorFx = new AuthorFx();
        authorFx.setId(author.getId());
        authorFx.setName(author.getName());
        authorFx.setSurname(author.getSurname());
        return authorFx;
    }

}
