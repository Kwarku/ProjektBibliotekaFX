package pl.biblioteka.projekt.database.models;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "AUTHORS")
public class Author implements BaseModel {
    public Author() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String nameAndSurname;

    // wlasna kolecja ksiazek dla danego autora
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public ForeignCollection<Book> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }
}
